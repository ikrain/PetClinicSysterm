package util;

import Bean.FeedBack;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author cc
 * @data 2020/6/13 - 14:30
 */
public class FBK {

    public void setFeedBack(String mess, HttpServletRequest req){
        ArrayList<FeedBack> arrayListFb = new ArrayList<FeedBack>();
        FeedBack fb = new FeedBack();
        fb.setMess(mess);
        arrayListFb.add(fb);
        req.setAttribute("listFb",arrayListFb);
    }

}
