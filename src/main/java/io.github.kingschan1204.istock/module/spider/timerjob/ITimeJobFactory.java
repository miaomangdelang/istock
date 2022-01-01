package io.github.kingschan1204.istock.module.spider.timerjob;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.ClearTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.CoreScheduleTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.DailyBasicTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.DividendTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.DyRoeAnalysisJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.FundHoldersTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.IndexTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.InfoTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.StockCodeTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.TopHolderTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.XueQiuDyTimerJobImpl;
import io.github.kingschan1204.istock.module.spider.timerjob.impl.YearReportTimerJobImpl;

import java.util.HashMap;

/**
 * 命令定时器简单工厂
 *
 * @author chenguoxiang
 * @create 2019-04-01 16:25
 **/
public class ITimeJobFactory {
    public enum TIMEJOB {
        CORE_SCHEDULE, INDEX, CLEAR, STOCKCODE, INFO, DAILY_BASIC, TOP_HOLDER, DY, YEAR_REPORT, DIVIDEND, DYROE, FUND_HOLDERS
    }

    private static HashMap<TIMEJOB, ITimerJob> map;

    static {
        map = new HashMap<>();
        map.put(TIMEJOB.CORE_SCHEDULE, new CoreScheduleTimerJobImpl());
        map.put(TIMEJOB.INDEX, new IndexTimerJobImpl());
        map.put(TIMEJOB.STOCKCODE, new StockCodeTimerJobImpl());
        map.put(TIMEJOB.CLEAR, new ClearTimerJobImpl());
        map.put(TIMEJOB.INFO, new InfoTimerJobImpl());
        map.put(TIMEJOB.DAILY_BASIC, new DailyBasicTimerJobImpl());
        map.put(TIMEJOB.TOP_HOLDER, new TopHolderTimerJobImpl());
        map.put(TIMEJOB.DY, new XueQiuDyTimerJobImpl());
        map.put(TIMEJOB.YEAR_REPORT, new YearReportTimerJobImpl());
        map.put(TIMEJOB.DIVIDEND, new DividendTimerJobImpl());
        map.put(TIMEJOB.DYROE, new DyRoeAnalysisJobImpl());
        map.put(TIMEJOB.FUND_HOLDERS, new FundHoldersTimerJobImpl());


    }

    /**
     * 得到指定的定时器
     *
     * @param timejob
     * @return
     */
    public static ITimerJob getJob(TIMEJOB timejob) {
        return map.get(timejob);
    }

    public static JSONArray getTasks() {
        JSONArray jsonArray = new JSONArray();
        JSONObject rows;
        for (TIMEJOB key : map.keySet()) {
            rows = new JSONObject();
            AbstractTimeJob job = (AbstractTimeJob) map.get(key);
            rows.put("id", key);
            rows.put("name", job.name);
            rows.put("status", job.status);
            jsonArray.add(rows);
        }
        return jsonArray;
    }

    /**
     * 得到任务状态
     *
     * @param key
     * @return
     */
    public static ITimerJob.STATUS getJobStatus(TIMEJOB key) {
        AbstractTimeJob job = (AbstractTimeJob) map.get(key);
        return job.status;
    }
}
