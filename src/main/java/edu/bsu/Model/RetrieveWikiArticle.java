package edu.bsu.Model;



import javax.swing.*;

public class RetrieveWikiArticle
{

    public String formatUrl(String userUrl)
    {
        String frontUrl = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=";
        String backUrl =  "&redirects=1&callback=&rvprop=timestamp%7Cuser&rvlimit=30";
        return frontUrl + userUrl + backUrl;

    }

    public String getUserUrl()
    {
        return JOptionPane.showInputDialog("Enter Wikipedia title: ");
    }
}
