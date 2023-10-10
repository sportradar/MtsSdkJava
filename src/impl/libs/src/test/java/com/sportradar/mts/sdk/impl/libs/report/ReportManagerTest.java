package com.sportradar.mts.sdk.impl.libs.report;

import com.google.common.cache.LoadingCache;
import com.google.common.io.Resources;
import com.sportradar.mts.sdk.api.AccessToken;
import com.sportradar.mts.sdk.api.CcfChange;
import com.sportradar.mts.sdk.api.enums.SourceType;
import com.sportradar.mts.sdk.api.impl.AccessTokenImpl;
import com.sportradar.mts.sdk.api.interfaces.ReportManager;
import com.sportradar.mts.sdk.api.rest.DataProvider;
import com.sportradar.mts.sdk.impl.libs.clientapi.MtsReportManagerImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;


@SuppressWarnings({"unchecked"})
public class ReportManagerTest {
    private static final String URL_DATA1 = "csv/ccf_history_change_export_data_1.csv";
    private static final String URL_DATA2 = "csv/ccf_history_change_export_data_2.csv";

    private static ReportManager mtsReportManager;
    private static DataProvider<InputStream> ccfHistoryChangeExportCsvDataProvider;
    private static LoadingCache<String, AccessToken> accessTokenCache;
    private static String stringData1;
    private static SimpleDateFormat formatter;

    @BeforeClass
    public static void beforeClass() throws ExecutionException, IOException {
        formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        ccfHistoryChangeExportCsvDataProvider = (DataProvider<InputStream>) mock(DataProvider.class);
        accessTokenCache = (LoadingCache<String, AccessToken>) mock(LoadingCache.class);
        stringData1 = Resources.toString(Resources.getResource(URL_DATA1), StandardCharsets.UTF_8);

        when(accessTokenCache.get(any())).thenReturn(new AccessTokenImpl("token", 0));
        when(ccfHistoryChangeExportCsvDataProvider.getData(any(AccessToken.class), anyString(), anyString(), anyString(), anyString(), anyString(), eq("sdkTest"))).thenReturn(
                ReportManagerTest.class.getClassLoader().getResourceAsStream(URL_DATA1));
        when(ccfHistoryChangeExportCsvDataProvider.getData(any(AccessToken.class), anyString(), anyString(), anyString(), anyString(), anyString(), eq("sdkTest2"))).thenReturn(
                ReportManagerTest.class.getClassLoader().getResourceAsStream(URL_DATA2),
                ReportManagerTest.class.getClassLoader().getResourceAsStream(URL_DATA2));
        when(ccfHistoryChangeExportCsvDataProvider.getData(any(AccessToken.class), anyString(), anyString(), anyString(), eq(""), eq(""), eq(""))).thenReturn(
                ReportManagerTest.class.getClassLoader().getResourceAsStream(URL_DATA1),
                ReportManagerTest.class.getClassLoader().getResourceAsStream(URL_DATA1),
                ReportManagerTest.class.getClassLoader().getResourceAsStream(URL_DATA1),
                ReportManagerTest.class.getClassLoader().getResourceAsStream(URL_DATA1),
                ReportManagerTest.class.getClassLoader().getResourceAsStream(URL_DATA1));
    }

    @Test
    public void testGetHistoryCcfChangeCsvExport_allFilters() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(-1, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", "pass");
        String startDatetime = "20210315000000";
        String endDatetime = "20210316000000";
        SourceType sourceType = SourceType.CUSTOMER;
        String sourceId = "sdkTest";
        List<Integer> subBookmakers = new ArrayList<>();
        subBookmakers.add(382);

        Date startDate = formatter.parse(startDatetime);
        Date endDate = formatter.parse(endDatetime);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            mtsReportManager.getHistoryCcfChangeCsvExport(outputStream, startDate, endDate, 7669, subBookmakers, sourceId, sourceType);
            Assert.assertNotNull(outputStream);
            String readData = new String(outputStream.toByteArray());
            Assert.assertEquals(readData, stringData1);
        }
    }

    @Test
    public void testGetHistoryCcfChangeCsvExport_minFilters() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(7669, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", "pass");
        String startDatetime = "20210315000000";
        String endDatetime = "20210316000000";
        Date startDate = formatter.parse(startDatetime);
        Date endDate = formatter.parse(endDatetime);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            mtsReportManager.getHistoryCcfChangeCsvExport(outputStream, startDate, endDate);
            Assert.assertNotNull(outputStream);
            String readData = new String(outputStream.toByteArray());
            Assert.assertEquals(readData, stringData1);
        }
    }

    @Test
    public void testGetHistoryCcfChangeCsvExport_minFilters_alt() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(7669, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", "pass");
        String startDatetime = "20210315000000";
        String endDatetime = "20210316000000";
        Date startDate = formatter.parse(startDatetime);
        Date endDate = formatter.parse(endDatetime);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            mtsReportManager.getHistoryCcfChangeCsvExport(outputStream, startDate, endDate, null, null, null, null);
            Assert.assertNotNull(outputStream);
            String readData = new String(outputStream.toByteArray());
            Assert.assertEquals(readData, stringData1);
        }
    }


    @Test(expected = NullPointerException.class)
    public void negativeTestGetHistoryCcfChangeCsvExport_1() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(-1, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", "pass"); //no default bookmakerId set!
        String startDatetime = "20210315000000";
        String endDatetime = "20210316000000";
        Date startDate = formatter.parse(startDatetime);
        Date endDate = formatter.parse(endDatetime);

        try(OutputStream outputStream = new ByteArrayOutputStream()) {
            mtsReportManager.getHistoryCcfChangeCsvExport(outputStream, startDate, endDate, null, null, null, null);
        }
    }

    @Test(expected = NullPointerException.class) //missing mandatory start and end date
    public void negativeTestGetHistoryCcfChangeCsvExport_2() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(7669, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", "pass"); //default bookmakerId is set!
        try(OutputStream outputStream = new ByteArrayOutputStream()) {
            mtsReportManager.getHistoryCcfChangeCsvExport(outputStream, null, null, null, null, null, null);
        }
    }

    @Test(expected = NullPointerException.class) //missing mandatory outputStream
    public void negativeTestGetHistoryCcfChangeCsvExport_3() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(7669, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", "pass"); //default bookmakerId is set!
        String startDatetime = "20210315000000";
        String endDatetime = "20210316000000";
        Date startDate = formatter.parse(startDatetime);
        Date endDate = formatter.parse(endDatetime);

        mtsReportManager.getHistoryCcfChangeCsvExport(null, startDate, endDate, 7669, null, null, null);
    }

    @Test(expected = NullPointerException.class) //missing mandatory username and password
    public void negativeTestGetHistoryCcfChangeCsvExport_4() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(7669, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, null, null); //default bookmakerId is set!, but not user and pass
        String startDatetime = "20210315000000";
        String endDatetime = "20210316000000";
        Date startDate = formatter.parse(startDatetime);
        Date endDate = formatter.parse(endDatetime);

        try(OutputStream outputStream = new ByteArrayOutputStream()) {
            mtsReportManager.getHistoryCcfChangeCsvExport(outputStream, startDate, endDate, 7669, null, null, null);
        }
    }

    @Test
    public void testGetHistoryCcfChangeCsvExport_mixUsernameAndPassword() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(7669, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", null); //default bookmakerId is set!, but not user and pass
        String startDatetime = "20210315000000";
        String endDatetime = "20210316000000";
        Date startDate = formatter.parse(startDatetime);
        Date endDate = formatter.parse(endDatetime);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            mtsReportManager.getHistoryCcfChangeCsvExport(outputStream, startDate, endDate, 7669, null, null, null, null, "password");
            Assert.assertNotNull(outputStream);
            String readData = new String(outputStream.toByteArray());
            Assert.assertEquals(readData, stringData1);
        }
    }

    @Test
    public void testGetHistoryCcfChangeCsvExportList_allFilters() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(-1, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", "pass");

        String startDatetime = "20210305000000";
        String endDatetime = "20211214000000";
        SourceType sourceType = SourceType.CUSTOMER;
        String sourceId = "sdkTest2";
        Integer bookmakerId = 7669;

        List<Integer> subBookmakers = new ArrayList<>();
        subBookmakers.add(382);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date startDate = dateFormat.parse(startDatetime);
        Date endDate = dateFormat.parse(endDatetime);

        List<CcfChange> ccfChangeList = mtsReportManager.getHistoryCcfChangeCsvExport(startDate, endDate, bookmakerId, subBookmakers, sourceId, sourceType);
        Assert.assertNotNull(ccfChangeList);
        assertDataSet1(ccfChangeList, "sdkTest2");
    }

    @Test
    public void testGetHistoryCcfChangeCsvExportList_startEndDate() throws Exception {
        mtsReportManager = new MtsReportManagerImpl(-1, accessTokenCache, ccfHistoryChangeExportCsvDataProvider, "user", "pass");

        String startDatetime = "20210305000000";
        String endDatetime = "20211214000000";

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date startDate = dateFormat.parse(startDatetime);
        Date endDate = dateFormat.parse(endDatetime);

        List<CcfChange> ccfChangeList = mtsReportManager.getHistoryCcfChangeCsvExport (startDate, endDate);
        Assert.assertNotNull(ccfChangeList);
        assertDataSet1(ccfChangeList, "sdkTest");
    }

    private void assertDataSet1(List<CcfChange> ccfChangeList, String sourceId) throws Exception {
        Assert.assertEquals(14, ccfChangeList.size());

        assertCcfChangeValues(ccfChangeList.get(0), formatter.parse("20210315095721"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.55, null, "1", "Soccer", false);
        assertCcfChangeValues(ccfChangeList.get(1), formatter.parse("20210315095721"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.63, null, "1", "Soccer", true);
        assertCcfChangeValues(ccfChangeList.get(2), formatter.parse("20210315095721"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.55, null, "sr:sport:1", null, false);
        assertCcfChangeValues(ccfChangeList.get(3), formatter.parse("20210315095721"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.63, null, "sr:sport:1", null, true);
        assertCcfChangeValues(ccfChangeList.get(4), formatter.parse("20210315093058"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.50, null, "1", "Soccer", false);
        assertCcfChangeValues(ccfChangeList.get(5), formatter.parse("20210315093058"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.60, null, "1", "Soccer", true);
        assertCcfChangeValues(ccfChangeList.get(6), formatter.parse("20210315093058"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.50, null, "sr:sport:1", null, false);
        assertCcfChangeValues(ccfChangeList.get(7), formatter.parse("20210315093058"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.60, null, "sr:sport:1", null, true);
        assertCcfChangeValues(ccfChangeList.get(8), formatter.parse("20210315092018"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.90, null, "43", "Alpine Skiing", false);
        assertCcfChangeValues(ccfChangeList.get(9), formatter.parse("20210315092018"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.70, null, "43", "Alpine Skiing", true);
        assertCcfChangeValues(ccfChangeList.get(10), formatter.parse("20210315092018"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.90, null, "sr:sport:43", null, false);
        assertCcfChangeValues(ccfChangeList.get(11), formatter.parse("20210315092018"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.70, null, "sr:sport:43", null, true);
        assertCcfChangeValues(ccfChangeList.get(12), formatter.parse("20210315091859"), 7669, 382, sourceId, SourceType.CUSTOMER, 0.90, 1.0, null, null, null);
        assertCcfChangeValues(ccfChangeList.get(13), formatter.parse("20210315091523"), 7669, 382, sourceId, SourceType.CUSTOMER, 1.0, null, null, null, null);
    }

    private void assertCcfChangeValues(CcfChange ccfChange, Date timestamp, Integer bookmakerId, Integer subBookmakerId, String sourceId, SourceType sourceType, Double ccf, Double previousCcf, String sportId, String sportName, Boolean live) {
        Assert.assertNotNull(ccfChange);
        Assert.assertEquals(ccfChange.getTimestamp(), timestamp);
        Assert.assertEquals(ccfChange.getBookmakerId(), bookmakerId);
        Assert.assertEquals(ccfChange.getSubBookmakerId(), subBookmakerId);
        Assert.assertEquals(ccfChange.getSourceId(), sourceId);
        Assert.assertEquals(ccfChange.getSourceType(), sourceType);
        Assert.assertEquals(ccfChange.getCcf(), ccf);
        Assert.assertEquals(ccfChange.getPreviousCcf(), previousCcf);
        Assert.assertEquals(ccfChange.getSportId(), sportId);
        Assert.assertEquals(ccfChange.getSportName(), sportName);
        Assert.assertEquals(ccfChange.getLive(), live);
    }

}
