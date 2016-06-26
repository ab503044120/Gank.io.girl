package me.lshare.recyclerviewdemo;

import java.util.List;

/**
 * Created by Lshare on 2016/6/25.
 */
public class ResponseItem {

    /**
     * count : 10
     * error : false
     * results : [{"desc":"10.27","publishedAt":"2015-10-27T02:43:16.906000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1exfffnlf2gj20hq0qoju9.jpg","who":"张涵宇"},{"desc":"5.20。\n520爱你，就给你甜甜的笑。今日特推！~~（づ￣3￣）づ╭❤～","publishedAt":"2015-05-21T10:05:06.527000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1esahpyv86sj20hs0qomzo.jpg","who":"张涵宇"},{"desc":"6.10\u2014\u2014（2）","publishedAt":"2015-06-10T04:12:03.656000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1esxxi1vbq0j20qo0hstcu.jpg","who":"张涵宇"},{"desc":"6.10\u2014\u2014（1）","publishedAt":"2015-06-10T04:12:04.272000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1esxxiw20rej20qo0hstcp.jpg","who":"张涵宇"},{"desc":"6.18","publishedAt":"2015-06-18T03:50:59.419000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1et80fw2p80j20qo0hsdj1.jpg","who":"张涵宇"},{"desc":"7.17","publishedAt":"2015-07-17T03:43:22.394000","readability":"","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1eu5jpf3lamj20f00mitck.jpg","who":"张涵宇"},{"desc":"7.3","publishedAt":"2015-07-03T04:12:02.419000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1etpfol394kj20qo0hsdiw.jpg","who":"张涵宇"},{"desc":"5.19\n","publishedAt":"2015-05-20T03:57:51.477000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bgw1es8c7ucr0rj20hs0qowhl.jpg","who":"张涵宇"},{"desc":"7.22","publishedAt":"2015-07-22T03:59:19.558000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034gw1eubet6h43qj20qo0hsadh.jpg","who":"daimajia"},{"desc":"8.18\u2014\u2014（1）","publishedAt":"2015-08-18T03:58:47.771000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1ev6jgvbt8ij20qo0hrdil.jpg","who":"张涵宇"}]
     */

    private int count;
    private boolean error;
    /**
     * desc : 10.27
     * publishedAt : 2015-10-27T02:43:16.906000
     * readability :
     * type : 福利
     * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1exfffnlf2gj20hq0qoju9.jpg
     * who : 张涵宇
     */

    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String desc;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getReadability() {
            return readability;
        }

        public void setReadability(String readability) {
            this.readability = readability;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
