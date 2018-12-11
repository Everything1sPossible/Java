package com.sjh.DesignPattern.StatusPattern;

/**
 * @author sjh
 * @Description: 黑名单状态
 * @date 2018/12/11 18:58
 */
public class BlackVoteState implements VoteState {
    @Override
    public void vote(String user, String voteIterm, VoteMachine voteMachine) {
        //记录黑名单中，禁止登录系统
        System.out.println("<" + user + ">" + " 进入黑名单，将禁止登录和使用本系统");
    }
}
