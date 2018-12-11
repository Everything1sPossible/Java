package com.sjh.DesignPattern.StatusPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sjh
 * @Description: 状态管理类
 * @date 2018/12/11 18:43
 */
public class VoteMachine {

    /**
     * 持有的状态
     */
    private VoteState voteState;
    /**
     * 记录用户投票的结果，Map<String,String>对应Map<用户名称，投票的选项>
     */
    private Map<String,String> mapVote = new HashMap<>();
    /**
     * 记录用户投票次数，Map<String,Integer>对应Map<用户名称，投票的次数>
     */
    private Map<String,Integer> mapVoteCount = new HashMap<>();

    public void vote(String user, String voteItem) {
        //1.为该用户增加投票次数
        //从记录中取出该用户已有的投票次数
        Integer oldVoteCount = mapVoteCount.get(user);
        if(oldVoteCount == null){
            oldVoteCount = 0;
        }
        oldVoteCount += 1;
        mapVoteCount.put(user, oldVoteCount);
        //2.判断该用户的投票类型，就相当于判断对应的状态
        //到底是正常投票、重复投票、恶意投票还是上黑名单的状态
        if(oldVoteCount == 1){ voteState = new NormalVoteState(); }
        else if(oldVoteCount > 1 && oldVoteCount < 5){ voteState = new RepeatVoteState(); }
        else if(oldVoteCount >= 5 && oldVoteCount <8){ voteState = new SpiteVoteState(); }
        else if(oldVoteCount > 8){ voteState = new BlackVoteState(); }
        //然后转调状态对象来进行相应的操作
        voteState.vote(user, voteItem, this);
    }

    public VoteState getVoteState() {
        return voteState;
    }

    public void setVoteState(VoteState voteState) {
        this.voteState = voteState;
    }

    public Map<String, String> getMapVote() {
        return mapVote;
    }

    public Map<String, Integer> getMapVoteCount() {
        return mapVoteCount;
    }
}
