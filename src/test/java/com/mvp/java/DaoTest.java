package com.mvp.java;

import com.mvp.java.repository.ApproveRejectCommReportDao;
import com.mvp.java.repository.CommonsDao;
import com.mvp.java.repository.SalesHierarchyDao;
import com.mvp.java.repository.TasksDao;
import com.mvp.java.vo.TaskSchedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class DaoTest {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    TasksDao tasksDao;

    @Autowired
    CommonsDao commonsDao;

    @Autowired
    SalesHierarchyDao salesHierarchyDao;

    @Autowired
    ApproveRejectCommReportDao approveRejectCommReportDao;


    @Test
    public void salesReportTest() {
        List r1 =  approveRejectCommReportDao.getSalesReports();

        List r2 =  approveRejectCommReportDao.getSalesReportData(50);
        approveRejectCommReportDao.toString();
    }

    //@Test
    public void commonDataLoadTest() {
        List r1 =  commonsDao.getDesignations();
        List r2 =  commonsDao.getRegions();
        List r3 =  commonsDao.getRoles();
        List r4 =  commonsDao.getPayoutFrequencies();
        List r5 =  commonsDao.getReportingManagers();
        List r6 =  salesHierarchyDao.getNonCachedSalesPersons();

        tasksDao.toString();
    }

        //@Test
    public void testSave() {
        try {
            tasksDao.saveSchedule(2, df.parse("2017-04-02"), df.parse("2017-04-12"), "M", "1,2,3", true, 0);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    //@Test
    public void testGetSchedule() {
        TaskSchedule ts = tasksDao.getTaskScheduleFor(5);
        ts.getEndDate();
    }

}
