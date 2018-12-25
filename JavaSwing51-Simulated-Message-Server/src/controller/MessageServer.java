/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import model.Message;

/**
 *
 * @author PC
 */
public class MessageServer {
    private Map<Integer, List<Message>> messages;
    private List<Message> selected;
    
    public MessageServer()
    {
        selected = new ArrayList<Message>();
        messages = new TreeMap<Integer, List<Message>>();
        List<Message> list = new ArrayList<Message>();
        list.add(new Message("The cat is missing","Have you seen Felix anywhere?"));
        list.add(new Message("See you later","Are we still meeting in the pub"));
        messages.put(0, list);
        
        list = new ArrayList<Message>();
        list.add(new Message("How about dinner later","Are you doing anything later on?"));
        messages.put(1, list);
    }
    
    public void setSelectedServers(Set<Integer> servers)
    {
        selected.clear();
        for(Integer id : servers)
        {
            if(messages.containsKey(id))
            {
                selected.addAll(messages.get(id));
            }
        }
    }
    
    public int getMessageCount()
    {
        return selected.size();
    }
}
    
