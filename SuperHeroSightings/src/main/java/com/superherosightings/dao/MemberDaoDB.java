package com.superherosightings.dao;

import com.superherosightings.dto.Member;
import com.superherosightings.dto.Organization;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoDB implements MemberDao {
    @Override
    public Member addMember(Member member) {
        return null;
    }

    @Override
    public Member getMemberById(int memberId) {
        return null;
    }

    @Override
    public void editMember(Member member) {

    }

    @Override
    public void deleteMemberById(int memberId) {

    }
    public static final class MemberMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberId(rs.getInt("member_id"));
            member.setMemberName(rs.getString("member_name"));
            member.setMemberTitle(rs.getString("member_title"));
            member.setOrgId(rs.getInt("organization_id"));
            return member;
        }
    }
}
