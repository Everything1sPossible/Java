package com.sjh.DesignPattern.StatusPattern;

/**
 * @author sjh
 * @Description: 正常投票状态
 * @date 2018/12/11 18:49
 */
public class NormalVoteState implements VoteState{
    @Override
    public void vote(String user, String voteIterm, VoteMachine voteMachine) {
        voteMachine.getMapVote().put(user, voteIterm);
        System.out.println("<" + user + ">" + "恭喜您投票成功！");
    }
}
