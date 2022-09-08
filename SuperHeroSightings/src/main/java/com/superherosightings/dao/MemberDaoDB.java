package com.superherosightings.dao;

import com.superherosightings.dto.Member;
import com.superherosightings.dto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoDB implements MemberDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDaoDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Member addMember(Member member) {
        final String INSERT_MEMBER = "INSERT INTO Member(member_name, member_title, organization_id)"
                +"VALUES(?,?,?)";
        jdbcTemplate.update(INSERT_MEMBER, member.getMemberName(), member.getMemberTitle(), member.getOrg().getOrgId());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        member.setMemberId(newId);

        return member;
    }
    private Organization getOrgForMember(Member member){
        final String SELECT_ORG_FOR_MEMBER = "SELECT * FROM Organization" + "JOIN Member ON Member.organization_id = Organization.organization_id WHERE Member.member_id = ?";
        return jdbcTemplate.queryForObject(SELECT_ORG_FOR_MEMBER, new OrganizationDaoDB.OrganizationMapper(),member.getMemberId());
    }
    @Override
    public Member getMemberById(int memberId) {
        final String SELECT_MEMBER_BY_ID = "SELECT * FROM Member" + "WHERE member_id = ?";
        Member member = jdbcTemplate.queryForObject(SELECT_MEMBER_BY_ID, new MemberMapper(), memberId);
        member.setOrg(getOrgForMember(member));
        return member;
    }

    @Override
    public void editMember(Member member) {
        final String UPDATE_MEMBER = "UPDATE Member SET member_name = ?, member_title = ?, organization_id = ?";
        jdbcTemplate.update(UPDATE_MEMBER, member.getMemberName(), member.getMemberTitle(), member.getOrg().getOrgId());
    }

    @Override
    public void deleteMemberById(int memberId) {
        final String DELETE_MEMBER = "DELETE FROM Member" + "WHERE member_id = ?";
        jdbcTemplate.update(DELETE_MEMBER, memberId);
    }
    public static final class MemberMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberId(rs.getInt("member_id"));
            member.setMemberName(rs.getString("member_name"));
            member.setMemberTitle(rs.getString("member_title"));
            return member;
        }
    }
}
