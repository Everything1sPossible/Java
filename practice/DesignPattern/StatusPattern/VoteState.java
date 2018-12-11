package com.sjh.DesignPattern.StatusPattern;

/**
 * @Description: 投票状态接口
 * @date 2018/12/11 18:42
 */
public interface VoteState {
    /**
     * @Description: user:投票人，voteIterm：选项，voteMachine：投票管理类
     * @author sjh
     * @date 2018/12/11 18:46
     */
    public void vote(String user, String voteIterm, VoteMachine voteMachine);
}
