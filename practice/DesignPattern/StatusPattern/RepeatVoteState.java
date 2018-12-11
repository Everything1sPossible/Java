package com.sjh.DesignPattern.StatusPattern;

/**
 * @author sjh
 * @Description: 重复刷票状态
 * @date 2018/12/11 18:57
 */
public class RepeatVoteState implements VoteState{
    @Override
    public void vote(String user, String voteIterm, VoteMachine voteMachine) {
        //重复投票，暂时不做处理
        System.out.println("<" + user + ">" + "请不要重复投票");
    }
}
