package edu.bsu.main;



import javax.swing.*;

public class RetrieveWikiArticle extends UserInput
{

    public String formatUrl(String userUrl)
    {
        String frontUrl = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=";
        String backUrl =  "&redirects=1&callback=&rvprop=timestamp%7Cuser&rvlimit=30";
        return frontUrl + userUrl + backUrl;

    }
}
