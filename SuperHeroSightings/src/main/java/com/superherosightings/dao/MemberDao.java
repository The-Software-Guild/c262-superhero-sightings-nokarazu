package com.superherosightings.dao;

import com.superherosightings.dto.Member;

public interface MemberDao {
    //Create
    public Member addMember(Member member);

    //Read
    public Member getMemberById(int memberId);

    //Update
    public void editMember(Member member);

    //Delete
    public void deleteMemberById(int memberId);
}
