package com.sjh.DesignPattern.StatusPattern;

/**
 * @author sjh
 * @Description: 恶意刷票状态
 * @date 2018/12/11 18:55
 */
public class SpiteVoteState implements VoteState{
    @Override
    public void vote(String user, String voteIterm, VoteMachine voteMachine) {
        // 恶意投票，取消用户的投票资格，并取消投票记录
        String str = voteMachine.getMapVote().get(user);
        if(str != null){
            voteMachine.getMapVote().remove(user);
        }
        System.out.println("<" + user + ">" + "你有恶意刷屏行为，取消投票资格");
    }
}
