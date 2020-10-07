import org.python.stdlib.datetime.DateTime;

public class Sprint3 {
    public static int countFuture(String[] isoDateTimes) {
        int count = 0;
        DateTime today = (DateTime) DateTime.today();

        for (String iso : isoDateTimes) {
            DateTime dateTime = (DateTime) DateTime.fromisoformat(iso);
            if (dateTime.__gt__(today).toBoolean()) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String[] isos = {
            "2020-08-31T19:16:15.000944", "2021-04-05T16:16:40.000969", "2020-08-23T21:16:01.000983", "2020-04-04T16:17:12.000995", "2020-09-06T14:16:58.000007", "2020-11-22T07:16:11.000018", "2021-01-23T09:16:55.000030", "2020-10-30T08:15:40.000040", "2021-01-28T06:15:39.000051", "2020-08-20T06:16:06.000062", "2020-11-09T08:17:06.000072", "2020-12-26T07:16:43.000083", "2021-03-12T20:16:14.000094", "2020-11-13T17:15:55.000105", "2020-04-13T18:17:13.000116", "2020-09-18T18:15:59.000126", "2020-08-29T12:17:07.000137", "2020-11-25T18:16:25.000148", "2021-04-13T03:15:54.000159", "2020-04-06T23:16:35.000169", "2020-06-20T17:15:14.000180", "2021-02-09T20:16:20.000191", "2020-08-11T03:16:44.000201", "2021-01-19T13:16:12.000212", "2021-03-19T14:15:23.000223", "2021-03-05T16:16:21.000233", "2020-05-11T07:16:41.000244", "2020-12-14T03:16:20.000255", "2020-08-30T05:16:49.000266", "2020-06-17T09:16:04.000276", "2021-02-26T08:15:51.000288", "2021-03-01T07:15:54.000298", "2021-02-09T08:16:55.000309", "2020-12-25T00:15:25.000320", "2021-03-14T13:15:47.000330", "2020-05-13T19:16:01.000341", "2020-07-18T12:16:15.000351", "2020-07-09T10:15:55.000362", "2021-01-04T14:16:20.000372", "2020-05-11T06:15:14.000384", "2020-04-15T09:17:06.000395", "2020-07-16T16:16:40.000406", "2020-09-03T03:15:57.000417", "2020-12-06T00:15:52.000428", "2020-07-20T22:16:30.000439", "2020-03-28T00:15:58.000449", "2020-08-17T15:15:27.000460", "2020-06-19T06:16:45.000471", "2020-12-09T09:16:57.000481", "2020-12-16T03:15:48.000492", "2021-01-18T01:15:58.000502", "2020-04-24T21:17:02.000512", "2020-04-15T17:16:00.000523", "2021-01-07T22:16:34.000534", "2020-10-28T23:15:18.000545", "2020-10-18T07:15:22.000556", "2020-12-17T17:16:05.000566", "2020-08-15T14:15:57.000577", "2020-04-22T10:16:58.000587", "2020-10-24T20:16:37.000598", "2020-06-22T20:16:49.000609", "2020-06-07T22:16:46.000629", "2020-06-12T23:15:36.000640", "2020-06-28T08:15:38.000652", "2020-06-10T16:15:22.000662", "2020-07-26T03:16:43.000674", "2020-04-28T00:16:17.000685", "2020-10-24T15:17:09.000695", "2021-03-16T09:16:07.000706", "2020-11-16T14:16:36.000717", "2020-12-07T14:16:38.000728", "2020-11-23T16:17:04.000738", "2020-08-24T05:16:23.000749", "2020-07-23T11:16:42.000760", "2020-10-03T11:15:54.000771", "2020-10-22T16:16:47.000781", "2021-01-05T14:16:51.000792", "2020-10-27T03:16:25.000802", "2021-04-18T09:16:47.000813", "2020-07-31T11:17:01.000824", "2020-08-14T21:17:07.000836", "2020-08-14T12:16:09.000847", "2020-07-01T09:15:25.000858", "2020-06-01T17:16:34.000868", "2020-08-01T13:15:35.000879", "2021-03-26T21:15:53.000889", "2020-05-26T03:15:28.000900", "2020-11-10T05:15:28.000911", "2020-04-28T20:16:03.000921", "2020-08-17T01:17:03.000932", "2021-04-02T15:16:01.000943", "2021-02-05T12:16:25.000954", "2020-03-31T12:16:53.000965", "2020-05-22T16:15:56.000975", "2021-03-30T21:16:45.000986", "2020-07-02T07:16:11.000997", "2020-07-02T12:15:51.000008", "2020-05-29T05:15:45.000019", "2020-04-30T19:16:17.000030", "2021-04-07T09:15:34.000041", "2021-03-07T17:15:17.000051", "2020-08-06T03:16:12.000062", "2020-05-06T00:16:44.000073", "2020-07-25T21:15:51.000084", "2020-12-13T19:15:43.000095", "2020-09-14T22:15:15.000105", "2021-01-08T04:16:42.000116", "2020-11-23T13:15:15.000127", "2020-08-23T00:16:44.000137", "2020-09-13T20:15:24.000148", "2020-05-06T15:16:49.000158", "2020-05-01T07:16:55.000169", "2021-02-15T13:16:35.000179", "2021-02-03T06:15:20.000190", "2020-11-21T07:16:25.000200", "2021-02-12T03:15:40.000211", "2020-11-09T09:15:26.000223", "2020-12-07T03:16:34.000239", "2020-11-20T22:16:33.000250", "2021-03-22T01:16:18.000260", "2020-08-13T16:16:02.000273", "2021-02-10T19:15:19.000283", "2020-09-29T05:15:21.000294", "2021-01-03T11:16:24.000305", "2020-04-22T04:16:49.000316", "2021-01-01T15:15:18.000327", "2020-06-10T08:16:24.000338", "2020-11-24T15:16:03.000349", "2020-04-26T05:15:20.000359", "2021-02-28T00:16:33.000370", "2021-01-31T09:15:54.000382", "2021-01-05T08:16:37.000393", "2020-04-24T11:15:14.000404", "2020-10-17T15:15:16.000414", "2020-12-20T18:15:50.000425", "2021-02-28T01:17:05.000436", "2020-08-26T01:16:34.000446", "2020-10-01T01:15:38.000457", "2020-11-10T22:16:31.000468", "2020-12-10T19:15:50.000479", "2021-02-08T21:15:47.000489", "2020-11-14T06:15:40.000500", "2021-04-12T00:16:36.000510", "2020-03-31T16:16:15.000521", "2020-08-11T00:15:17.000531", "2021-03-12T20:16:57.000542", "2020-05-09T19:15:54.000553", "2020-06-01T04:15:48.000563", "2020-04-07T05:15:30.000574", "2020-05-16T04:15:16.000585", "2021-01-22T05:16:24.000595", "2020-12-02T12:15:17.000609", "2020-05-08T15:16:48.000620", "2020-07-11T01:15:16.000631", "2021-02-26T20:17:09.000642", "2021-03-11T07:15:17.000653", "2021-03-14T10:15:52.000666", "2021-02-02T16:15:29.000677", "2020-04-08T05:15:44.000688", "2020-10-27T13:15:21.000698", "2021-04-13T03:15:39.000709", "2020-11-03T07:16:49.000720", "2020-03-24T11:16:56.000731", "2020-06-22T09:15:26.000742", "2021-03-28T06:15:59.000753", "2020-11-22T16:16:05.000763", "2020-10-04T08:16:09.000774", "2020-11-12T19:16:07.000785", "2020-11-20T06:16:07.000796", "2021-03-20T22:16:40.000806", "2020-06-14T00:16:20.000817", "2020-10-09T08:16:44.000828", "2020-08-20T04:17:03.000839", "2020-12-02T22:16:16.000849", "2020-12-14T05:16:37.000860", "2021-03-13T11:16:40.000870", "2020-09-01T03:16:29.000881", "2020-04-20T01:16:36.000891", "2020-10-06T11:15:46.000902", "2020-12-19T19:16:35.000912", "2021-04-22T23:16:12.000923", "2020-11-07T22:16:54.000933", "2020-09-23T00:16:42.000944", "2020-05-11T01:17:07.000955", "2020-04-10T04:16:52.000966", "2021-03-20T04:15:27.000976", "2020-12-29T15:17:11.000987", "2020-11-06T21:17:09.000997", "2020-08-26T17:15:46.000008", "2020-11-03T16:17:06.000019", "2021-04-17T21:16:50.000030", "2020-04-17T19:15:45.000041", "2021-04-09T22:16:18.000051", "2020-08-09T00:16:24.000062", "2021-01-19T10:16:00.000072", "2020-11-21T12:15:56.000083", "2020-07-01T07:16:40.000094", "2020-04-25T17:17:00.000104", "2020-07-17T10:15:48.000115", "2020-11-07T13:15:21.000126", "2020-11-12T16:15:58.000136", "2020-11-25T15:16:17.000146", "2020-10-12T09:15:51.000157", "2021-01-23T13:15:21.000167", "2020-08-25T07:16:11.000178", "2020-11-09T00:15:49.000188", "2021-04-07T15:16:38.000199", "2020-03-28T19:16:28.000209", "2020-09-14T15:16:32.000220", "2020-12-26T08:15:26.000230", "2020-07-31T05:16:15.000241", "2020-05-27T13:17:06.000252", "2020-04-27T20:16:00.000262", "2020-08-28T20:15:20.000273", "2020-09-13T18:15:34.000283", "2020-08-17T06:16:49.000294", "2020-12-15T18:15:52.000304", "2021-04-05T22:16:25.000315", "2020-04-05T20:16:02.000326", "2020-04-07T22:16:36.000345", "2020-10-26T21:16:31.000357", "2020-03-27T22:15:18.000368", "2020-07-06T14:16:52.000378", "2020-04-14T06:15:51.000389", "2020-04-29T07:16:17.000400", "2020-11-28T15:15:14.000410", "2020-08-01T20:17:12.000421", "2021-04-19T14:16:42.000431", "2020-11-24T17:15:33.000442", "2021-01-26T16:16:53.000452", "2020-04-16T08:16:26.000463", "2020-08-03T00:15:33.000473", "2020-04-19T17:15:24.000484", "2020-11-14T07:16:10.000494", "2020-04-16T04:17:13.000505", "2020-10-03T04:16:12.000516", "2020-11-21T07:15:49.000526", "2021-03-01T03:15:33.000537", "2020-08-28T17:16:36.000548", "2020-12-19T01:16:06.000558", "2021-02-18T10:15:41.000569", "2020-11-14T12:15:45.000579", "2020-05-09T17:16:45.000590", "2021-03-11T21:15:48.000600", "2020-11-09T11:15:39.000611", "2020-05-13T01:16:12.000621", "2021-02-19T21:17:01.000632", "2020-10-12T21:15:20.000643", "2020-05-22T09:15:19.000653", "2020-05-04T14:15:42.000664", "2020-12-11T19:15:29.000674", "2020-06-09T11:16:56.000685", "2021-03-27T13:16:03.000696", "2020-04-28T03:16:46.000707", "2021-01-12T00:15:25.000717", "2021-04-14T23:16:11.000728", "2021-03-06T06:16:58.000739", "2020-04-13T20:16:45.000752", "2020-12-30T05:16:16.000763", "2020-09-30T06:16:12.000774", "2020-05-03T20:16:36.000785", "2020-07-28T21:15:17.000795", "2020-05-03T18:15:21.000806", "2020-08-14T11:17:01.000817", "2020-04-17T15:16:35.000828", "2020-04-29T03:16:02.000839", "2021-02-20T07:17:05.000850", "2020-03-29T09:15:19.000861", "2020-07-20T06:15:43.000871", "2020-08-26T22:17:00.000882", "2020-04-29T13:16:50.000893", "2020-06-14T18:15:35.000903", "2020-08-05T17:16:46.000914", "2020-11-23T09:16:14.000924", "2020-04-25T03:16:12.000935", "2021-01-07T17:17:03.000956", "2020-11-08T07:16:03.000968", "2020-05-24T08:16:50.000978", "2021-01-18T21:16:55.000013", "2020-12-30T09:15:55.000024", "2020-12-05T08:15:57.000035", "2020-08-25T15:16:23.000046", "2020-11-27T01:16:29.000056", "2021-03-15T06:17:09.000067", "2021-01-18T05:16:52.000078", "2020-05-05T16:15:50.000089", "2020-09-27T23:15:50.000100", "2020-12-04T11:16:29.000111", "2021-03-12T10:15:58.000122", "2021-04-03T07:16:34.000133", "2020-10-29T09:16:17.000145", "2020-05-31T00:16:08.000155", "2020-06-18T08:16:57.000166", "2020-10-03T05:16:35.000204", "2020-07-18T12:16:51.000215", "2020-04-15T21:16:18.000226", "2020-05-06T06:16:13.000237", "2021-04-12T15:16:03.000248", "2021-04-06T23:16:00.000259", "2020-08-11T18:16:08.000270", "2021-02-11T07:16:16.000280", "2021-01-09T19:17:02.000291", "2020-05-19T08:16:51.000302", "2020-04-07T08:15:55.000313", "2021-03-08T21:15:53.000324", "2020-08-24T09:16:57.000334", "2021-04-06T00:15:42.000345", "2021-02-28T04:17:13.000356", "2020-09-23T15:16:03.000367", "2020-08-12T17:16:30.000377", "2021-03-04T10:15:40.000388", "2020-11-13T18:15:37.000399", "2020-10-09T09:16:09.000410", "2021-02-17T21:17:10.000420", "2021-04-20T03:15:29.000431", "2020-09-10T03:16:02.000442", "2020-12-09T14:16:28.000453", "2020-09-17T01:16:15.000463", "2020-05-28T17:17:02.000474", "2020-04-23T20:16:12.000485", "2020-12-09T19:16:06.000496", "2021-02-17T09:16:34.000507", "2020-11-11T10:17:08.000517", "2021-03-04T11:16:20.000528", "2021-01-28T01:17:08.000539", "2021-04-18T14:17:01.000550", "2020-07-21T05:17:12.000560", "2020-09-20T11:16:56.000571", "2020-06-02T06:15:16.000582", "2020-05-04T23:15:33.000593", "2020-07-17T01:15:18.000603", "2020-10-04T00:15:58.000614", "2020-06-12T04:16:40.000625", "2020-08-22T06:15:36.000636", "2020-08-07T08:15:47.000647", "2021-01-17T20:15:44.000658", "2020-09-16T18:16:47.000669", "2021-02-03T11:15:38.000680", "2020-08-28T04:16:20.000691", "2020-04-22T21:16:41.000702", "2020-05-29T00:15:55.000713", "2021-03-29T19:15:38.000724", "2020-08-07T22:16:39.000734", "2021-02-20T07:16:34.000745", "2020-05-29T15:15:14.000771", "2020-07-26T12:17:12.000781", "2020-06-14T07:16:37.000791", "2020-05-11T13:16:27.000802", "2021-02-14T09:16:30.000812", "2020-05-20T16:15:57.000823", "2020-05-03T05:16:02.000833", "2021-03-14T05:16:00.000844", "2021-03-31T01:15:29.000854", "2020-04-12T12:17:06.000864", "2021-02-25T11:16:50.000875", "2020-04-14T20:15:27.000885", "2020-08-28T16:17:07.000896", "2020-07-29T13:15:51.000906", "2021-04-17T20:16:15.000917", "2021-02-22T01:16:46.000927", "2020-07-23T08:16:42.000938", "2021-03-30T10:17:03.000948", "2020-12-29T04:16:54.000958", "2021-02-13T01:16:33.000969", "2020-12-10T18:17:02.000979", "2020-04-12T18:15:58.000989", "2020-04-27T06:16:54.000010", "2020-12-13T03:15:17.000020", "2020-07-13T16:16:22.000031", "2021-02-03T23:16:59.000041", "2021-01-24T12:15:23.000052", "2020-04-19T21:15:46.000062", "2020-06-10T11:17:12.000072", "2020-08-25T18:17:00.000082", "2021-01-07T16:15:59.000093", "2020-07-19T09:15:32.000103", "2021-02-27T07:15:19.000114", "2020-06-06T19:16:46.000124", "2021-04-15T03:16:34.000135", "2020-10-28T23:16:31.000145", "2020-11-23T16:16:27.000156", "2021-01-16T04:16:11.000167", "2020-05-12T06:15:32.000178", "2020-11-19T08:17:11.000188", "2020-06-08T20:16:49.000198", "2020-12-22T13:16:02.000209", "2020-06-04T22:15:42.000219", "2021-04-14T01:15:35.000229", "2020-04-29T05:16:45.000240", "2021-02-16T23:16:39.000250", "2021-01-30T14:16:57.000261", "2021-04-15T10:16:53.000271", "2020-06-24T22:15:19.000282", "2020-05-10T21:15:25.000292", "2021-02-04T13:15:15.000303", "2021-04-22T23:16:55.000313", "2021-01-23T13:15:42.000323", "2020-07-19T07:15:39.000334", "2021-04-12T04:16:04.000344", "2020-06-10T14:16:31.000355", "2020-09-22T13:15:46.000365", "2021-01-30T10:16:12.000376", "2021-01-24T07:16:15.000386", "2020-09-30T03:15:54.000397", "2020-08-28T15:16:00.000407", "2020-07-25T20:16:49.000417", "2020-07-25T13:17:10.000428", "2020-12-08T20:15:38.000438", "2020-05-24T09:16:50.000449", "2020-07-16T15:16:14.000459", "2020-12-08T15:16:44.000470", "2020-11-02T14:16:04.000480", "2020-07-02T12:15:26.000490", "2020-09-01T13:16:31.000500", "2020-11-26T18:16:26.000510", "2020-05-23T19:16:49.000520", "2021-02-19T04:16:47.000531", "2020-04-06T22:16:18.000541", "2021-02-10T13:16:32.000551", "2020-06-20T17:17:01.000562", "2020-10-19T00:15:30.000572", "2021-01-22T06:15:22.000582", "2020-12-24T13:15:42.000593", "2021-01-04T08:15:18.000603", "2020-09-30T17:17:08.000613", "2020-06-15T17:15:49.000624", "2020-03-25T16:16:58.000634", "2020-11-22T21:16:32.000645", "2020-10-20T20:17:06.000655", "2020-04-15T17:16:24.000665", "2021-01-24T10:16:29.000675", "2020-06-10T15:15:32.000685", "2020-07-12T13:16:36.000696", "2021-02-09T01:15:43.000706", "2021-02-08T08:15:56.000716", "2020-11-06T00:15:26.000727", "2020-12-26T14:17:13.000738", "2021-03-16T15:15:44.000748", "2020-09-06T01:16:37.000759", "2020-08-22T07:16:06.000769", "2021-01-09T05:15:32.000780", "2020-11-17T11:15:15.000791", "2020-10-14T09:16:29.000801", "2020-06-15T11:17:00.000811", "2020-09-22T09:15:30.000822", "2021-03-09T08:16:18.000832", "2020-11-08T08:17:11.000843", "2020-12-17T22:16:16.000853", "2020-08-18T10:15:43.000863", "2020-10-08T11:15:23.000874", "2020-09-24T11:15:35.000884", "2021-01-11T21:15:59.000894", "2020-10-10T12:16:49.000906", "2020-07-22T09:16:20.000916", "2020-10-16T13:15:41.000927", "2020-06-23T06:15:35.000937", "2020-09-09T18:15:23.000948", "2021-03-27T00:15:47.000958", "2020-08-05T07:17:10.000968", "2020-10-18T11:17:06.000979", "2021-03-03T09:15:37.000989", "2020-10-21T05:16:35.000999", "2020-12-22T22:16:06.000010", "2021-02-17T21:15:35.000020", "2020-11-25T16:15:58.000031", "2021-03-02T12:15:56.000041", "2020-06-16T23:16:41.000051", "2020-06-30T00:16:34.000062", "2021-03-09T07:16:56.000072", "2020-12-04T07:17:03.000083", "2020-12-16T03:16:07.000094", "2020-11-08T22:15:43.000104", "2020-04-20T01:16:10.000115", "2021-02-05T19:16:17.000125", "2020-10-22T16:16:51.000135", "2020-05-21T22:15:55.000146", "2020-06-14T05:16:41.000157", "2021-02-18T12:15:29.000167", "2020-07-23T21:16:02.000178", "2020-03-27T04:17:05.000188", "2021-01-26T15:16:25.000199", "2020-08-08T14:16:09.000209", "2020-11-05T17:16:46.000220", "2021-03-30T04:16:00.000230", "2020-03-22T21:16:12.000241", "2021-01-08T07:17:06.000252", "2020-10-15T18:15:35.000263", "2020-04-09T16:15:34.000273", "2021-01-09T13:15:52.000284", "2020-05-17T20:17:07.000294", "2020-12-25T05:15:46.000305", "2021-01-13T14:17:12.000315", "2020-10-01T12:16:32.000331", "2021-04-02T15:16:26.000342", "2020-10-31T06:16:23.000352", "2020-10-31T12:15:26.000362", "2020-07-15T20:16:16.000373", "2021-01-25T04:16:59.000454", "2020-10-06T17:16:25.000483", "2020-11-16T18:15:21.000498", "2020-09-13T21:17:13.000511", "2020-06-10T21:16:27.000522", "2020-06-22T11:16:27.000533", "2020-09-25T19:16:30.000544", "2021-01-14T06:15:29.000555", "2021-04-01T22:17:05.000565", "2020-11-28T13:15:50.000576", "2021-01-01T21:16:39.000589", "2021-02-20T06:16:31.000600", "2020-11-12T04:17:11.000611", "2020-06-08T20:16:57.000621", "2020-12-23T14:16:09.000632", "2020-08-20T13:15:55.000643", "2020-10-28T03:16:12.000653", "2021-02-01T06:16:52.000664", "2021-02-06T00:16:12.000674", "2020-07-10T01:16:37.000685", "2021-02-06T23:16:30.000695", "2021-04-07T22:15:56.000706", "2020-10-24T13:15:54.000716", "2021-04-17T09:15:52.000726", "2020-08-17T05:16:47.000737", "2020-09-05T06:16:47.000748", "2020-09-09T03:16:31.000758", "2020-07-11T22:15:24.000769", "2020-05-01T00:15:39.000779", "2020-04-10T23:17:11.000789", "2020-06-14T11:17:08.000800", "2021-04-01T03:16:38.000810", "2020-09-21T15:16:19.000820", "2020-12-11T04:15:33.000831", "2020-07-16T01:15:40.000841", "2020-12-22T19:16:19.000852", "2021-01-07T08:16:14.000862", "2021-04-17T20:15:46.000872", "2020-07-13T22:15:44.000883", "2020-06-02T22:16:17.000893", "2020-11-05T21:17:00.000904", "2021-03-11T17:16:02.000928", "2021-03-02T15:16:35.000939", "2020-11-12T13:15:29.000948", "2020-11-08T00:16:13.000958", "2021-01-19T16:15:29.000968", "2020-09-18T11:15:58.000978", "2020-06-06T14:16:00.000988", "2020-09-24T17:15:40.000999", "2020-10-12T18:15:37.000008", "2020-12-07T15:15:19.000018", "2020-09-06T21:16:16.000031", "2020-04-06T04:16:15.000041", "2020-11-23T20:16:31.000052", "2020-07-22T13:16:30.000062", "2021-02-09T11:16:39.000072", "2021-03-13T00:17:02.000082", "2020-12-29T05:15:48.000092", "2020-12-17T09:15:52.000102", "2020-04-11T13:16:11.000112", "2020-04-02T05:16:10.000122", "2021-01-30T16:15:56.000132", "2020-11-05T16:16:35.000142", "2020-10-10T20:16:21.000152", "2020-05-10T06:17:00.000162", "2020-03-29T00:16:53.000172", "2020-05-29T23:16:32.000183", "2021-04-15T00:15:30.000192", "2020-09-07T19:16:46.000204", "2020-11-06T03:15:46.000214", "2020-09-23T21:15:20.000224", "2020-12-23T20:15:39.000234", "2020-05-21T17:17:05.000244", "2020-12-05T03:15:40.000255", "2021-02-10T15:16:46.000265", "2020-09-30T19:16:46.000275", "2021-01-26T18:17:05.000285", "2020-09-09T07:17:12.000295", "2020-06-24T13:16:27.000305", "2021-01-31T01:15:14.000316", "2020-09-28T22:16:18.000326", "2020-03-29T23:15:20.000336", "2020-10-07T03:16:57.000346", "2021-04-08T03:17:04.000356", "2020-09-25T03:16:48.000366", "2020-03-29T01:15:47.000376", "2020-04-01T23:16:33.000386", "2021-01-17T09:15:48.000396", "2020-11-28T20:16:57.000406", "2021-02-26T15:17:01.000416", "2021-02-03T05:16:55.000426", "2020-05-27T04:15:29.000437", "2020-12-03T19:16:03.000447", "2020-05-01T13:16:51.000457", "2020-07-01T19:16:06.000467", "2020-09-21T13:15:52.000477", "2020-10-25T04:17:06.000488", "2020-08-11T13:16:50.000497", "2021-03-02T08:15:35.000508", "2021-01-31T10:16:50.000518", "2020-10-23T23:16:04.000528", "2021-01-03T23:15:44.000538", "2020-07-16T23:17:13.000548", "2020-10-28T16:17:09.000558", "2020-06-10T12:17:00.000568", "2020-08-04T08:15:48.000578", "2021-03-25T13:15:29.000589", "2020-05-15T20:16:37.000599", "2021-02-02T14:15:28.000609", "2020-07-11T01:16:01.000619", "2020-05-01T04:17:09.000630", "2021-02-03T01:16:00.000640", "2020-05-20T14:16:49.000651", "2021-04-09T07:17:11.000662", "2020-09-26T11:16:41.000672", "2020-12-13T10:16:04.000682", "2020-12-05T04:16:32.000692", "2021-01-20T16:15:31.000702", "2020-07-20T16:16:02.000714", "2020-08-21T03:16:20.000724", "2020-07-02T23:15:53.000734", "2020-05-15T12:16:29.000743", "2021-02-20T07:15:42.000754", "2020-10-27T14:15:48.000764", "2020-12-15T16:16:36.000774", "2020-06-28T05:16:41.000784", "2020-10-14T18:17:13.000794", "2020-10-03T16:16:07.000804", "2020-09-21T11:16:45.000815", "2021-04-14T17:17:12.000825", "2021-03-19T16:16:22.000835", "2021-03-10T14:15:51.000845", "2020-06-18T13:16:29.000855", "2021-01-13T09:15:53.000865", "2021-02-19T13:15:36.000875", "2020-10-21T21:15:23.000885", "2020-09-29T09:15:28.000895", "2020-03-22T06:15:47.000906", "2020-04-03T20:15:39.000916", "2020-06-06T06:16:52.000926", "2020-08-16T07:15:47.000936", "2020-07-01T15:16:37.000947", "2020-08-07T01:17:03.000956", "2020-04-15T09:17:07.000967", "2021-02-25T03:15:55.000977", "2020-09-06T01:16:15.000987", "2021-01-06T17:15:21.000997", "2020-08-13T03:16:37.000007", "2020-05-25T05:15:50.000018", "2020-08-07T11:16:40.000028", "2020-05-12T05:15:37.000038", "2021-02-07T15:16:29.000048", "2020-06-09T18:16:32.000059", "2020-04-07T03:16:08.000069", "2021-01-10T16:16:52.000079", "2020-07-21T22:15:27.000090", "2021-01-20T11:15:18.000100", "2020-10-22T04:15:23.000110", "2021-03-08T22:16:49.000121", "2021-04-08T14:15:19.000131", "2020-07-21T01:16:40.000141", "2020-12-15T14:15:38.000151", "2020-11-30T21:15:31.000162", "2020-05-22T12:15:58.000172", "2020-03-31T13:16:53.000182", "2020-04-14T11:16:20.000193", "2020-10-01T14:16:33.000203", "2020-04-18T19:15:33.000213", "2021-01-04T18:15:28.000223", "2021-01-01T09:16:06.000233", "2020-12-20T16:16:46.000243", "2021-01-30T16:16:45.000253", "2020-05-23T14:16:15.000264", "2020-06-21T04:16:06.000274", "2020-09-25T08:16:25.000285", "2020-11-27T15:17:08.000295", "2021-01-31T03:16:47.000305", "2020-11-16T19:17:07.000315", "2020-10-05T19:15:18.000325", "2021-02-16T07:16:17.000335", "2020-06-18T06:17:09.000345", "2020-12-22T13:15:19.000356", "2020-07-09T09:15:23.000366", "2021-04-11T17:15:26.000376", "2020-07-22T01:15:54.000386", "2021-03-13T08:16:22.000396", "2020-11-20T08:15:53.000407", "2020-05-28T07:16:01.000417", "2020-11-03T14:17:04.000427", "2020-05-12T19:15:19.000437", "2020-10-20T04:15:21.000448", "2020-07-05T20:16:39.000458", "2020-08-05T13:16:20.000468", "2020-09-12T18:15:26.000478", "2021-02-26T17:16:01.000488", "2021-02-12T22:15:24.000498", "2020-11-17T01:15:39.000508", "2020-06-21T05:15:31.000518", "2021-04-16T07:16:37.000529", "2020-07-13T11:15:13.000539", "2020-12-13T17:15:20.000549", "2020-12-05T09:16:30.000560", "2020-08-11T07:16:01.000570", "2021-01-22T23:16:13.000580", "2021-03-14T04:16:21.000591", "2020-05-04T03:15:13.000601", "2021-03-22T16:16:11.000610", "2021-02-27T04:15:41.000621", "2020-08-18T22:17:01.000631", "2020-05-19T07:16:23.000641", "2021-04-02T18:15:28.000651", "2020-05-14T21:16:26.000661", "2020-08-12T07:15:31.000672", "2020-12-14T06:15:17.000682", "2020-11-24T20:16:16.000692", "2020-11-19T21:15:48.000702", "2021-01-21T13:15:53.000712", "2020-11-04T01:16:27.000722", "2021-04-01T11:16:04.000747", "2020-04-26T16:16:23.000758", "2020-08-02T19:16:16.000768", "2020-05-27T20:17:02.000778", "2020-04-18T23:17:09.000789", "2021-03-08T13:16:30.000799", "2020-11-30T20:17:11.000810", "2020-10-11T22:15:59.000820", "2021-01-06T22:16:04.000830", "2020-06-16T03:15:23.000840", "2021-04-25T13:16:01.000851", "2020-06-11T14:15:23.000861", "2021-03-21T22:16:11.000871", "2020-04-26T01:16:48.000882", "2020-07-18T13:16:11.000892", "2021-01-04T01:16:24.000903", "2020-10-08T11:15:41.000913", "2021-02-14T20:17:04.000923", "2020-03-26T23:16:38.000934", "2020-03-22T20:15:54.000944", "2021-04-02T11:16:59.000955", "2020-11-04T13:16:52.000965", "2021-02-10T19:15:24.000976", "2020-12-18T16:16:46.000986", "2020-12-06T13:16:25.000997", "2020-08-26T09:17:03.000007", "2020-09-19T09:16:55.000018", "2020-09-04T05:16:38.000028", "2020-05-01T12:16:45.000039", "2020-11-12T05:16:00.000049", "2020-04-27T00:16:12.000060", "2020-11-20T08:16:00.000071", "2020-12-14T08:15:32.000081", "2020-06-10T07:15:48.000092", "2020-12-14T22:15:54.000102", "2020-06-30T23:17:03.000113", "2020-07-03T15:16:53.000123", "2021-03-24T12:15:58.000133", "2020-04-13T16:16:08.000144", "2021-02-21T05:17:08.000154", "2020-10-20T05:16:13.000165", "2020-10-26T21:15:22.000175", "2021-01-30T05:17:12.000185", "2020-07-18T19:16:54.000196", "2021-01-28T15:15:29.000206", "2020-03-24T18:15:17.000217", "2021-04-09T13:17:08.000227", "2020-05-19T01:16:33.000238", "2020-06-12T03:17:09.000248", "2020-09-28T15:15:33.000259", "2020-09-11T06:15:54.000269", "2020-12-17T03:16:39.000280", "2021-03-30T22:16:32.000290", "2020-06-11T19:17:06.000301", "2021-03-11T10:15:32.000311", "2021-01-18T09:16:32.000321", "2021-02-26T14:15:49.000332", "2020-11-20T15:16:26.000342", "2020-05-12T06:17:10.000352", "2020-08-27T18:17:04.000363", "2020-09-02T17:16:22.000373", "2020-07-21T13:16:12.000383", "2020-08-21T13:15:24.000394", "2020-10-26T06:16:40.000404", "2021-01-01T18:16:57.000414", "2020-07-05T13:16:57.000424", "2020-05-09T09:17:05.000435", "2020-08-03T09:16:32.000445", "2020-04-23T22:16:49.000456", "2020-09-06T19:16:50.000467", "2020-10-18T23:15:20.000478", "2020-04-02T05:16:45.000488", "2020-06-01T21:16:06.000500", "2020-10-28T11:16:30.000510", "2020-03-28T19:15:41.000521", "2020-10-21T06:15:35.000532", "2020-10-11T07:17:13.000543", "2020-04-28T08:16:05.000554", "2020-04-21T09:15:19.000564", "2021-02-21T03:15:38.000578", "2020-09-04T08:15:53.000624", "2020-04-02T20:16:00.000645", "2020-12-26T18:15:38.000659", "2020-09-01T16:15:21.000670", "2020-03-26T05:15:19.000681", "2020-11-11T04:16:24.000692", "2020-10-17T16:15:49.000703", "2020-05-17T03:16:13.000714", "2020-05-10T18:16:13.000724", "2021-02-14T11:15:19.000735", "2021-03-09T15:15:48.000746", "2020-05-27T01:15:51.000756", "2021-04-05T13:16:13.000766", "2020-11-27T15:16:28.000791", "2020-12-06T22:16:34.000801", "2020-03-27T18:15:13.000812", "2021-04-01T09:15:44.000822", "2020-04-10T11:16:19.000832", "2020-08-27T08:17:02.000842", "2020-03-25T12:15:32.000852", "2021-02-16T14:15:14.000862", "2021-04-14T21:16:48.000872", "2020-09-02T07:16:37.000882", "2020-04-05T23:15:20.000892", "2021-01-10T08:16:32.000902", "2020-06-01T05:15:36.000912", "2021-01-05T14:16:12.000922", "2021-01-09T17:16:19.000932", "2021-03-10T23:16:02.000942", "2020-07-30T08:16:59.000952", "2020-04-05T12:15:54.000962", "2020-06-04T13:17:01.000972", "2021-03-01T15:16:17.000985", "2021-04-13T05:16:45.000995", "2020-05-21T04:16:49.000005", "2020-12-06T06:16:59.000015", "2020-05-08T06:16:55.000025", "2020-06-03T15:17:01.000035", "2020-08-28T20:16:55.000045", "2020-04-14T00:17:05.000055", "2020-05-02T19:15:20.000065", "2021-03-25T10:16:17.000075", "2021-04-02T01:16:47.000085", "2021-02-22T08:16:24.000096", "2020-08-10T07:17:02.000105", "2020-07-20T11:15:30.000115", "2020-08-14T23:16:02.000125", "2020-12-18T06:17:02.000135", "2020-08-20T14:15:16.000146", "2021-01-19T19:17:12.000156", "2020-05-14T18:15:44.000166", "2021-04-14T15:15:44.000175", "2021-03-19T19:15:25.000186", "2020-08-06T19:15:29.000196", "2020-08-06T21:16:26.000206", "2021-02-23T15:16:47.000216", "2020-09-13T12:15:53.000226", "2020-07-23T00:15:46.000236", "2021-01-20T21:15:45.000246", "2020-04-26T05:15:24.000257", "2020-12-18T03:16:13.000267", "2021-04-03T11:16:11.000277", "2021-03-06T18:15:23.000287", "2021-03-05T16:16:07.000296", "2020-06-10T18:16:03.000306", "2020-09-14T18:16:30.000316", "2021-01-23T18:15:37.000326", "2020-09-04T09:15:33.000336", "2020-03-26T03:16:33.000346", "2020-06-17T12:15:54.000356", "2021-01-16T23:15:59.000366", "2021-02-13T14:16:38.000376", "2020-05-29T14:15:26.000386", "2020-07-29T03:16:15.000396", "2020-09-29T23:15:32.000406", "2020-08-07T04:15:42.000416", "2020-12-11T14:16:22.000426", "2020-07-30T01:16:46.000436", "2020-10-02T18:16:04.000446", "2020-08-17T07:16:13.000455", "2020-06-15T01:15:16.000465", "2021-04-02T01:16:54.000475", "2020-09-30T07:16:17.000485", "2021-03-09T22:15:53.000495", "2020-05-31T10:16:41.000505", "2020-10-28T13:17:00.000515", "2020-04-06T22:17:08.000525", "2020-04-16T19:15:54.000535", "2020-04-23T15:16:52.000545", "2021-04-10T12:16:19.000555", "2020-04-03T04:16:55.000565", "2020-11-09T16:16:24.000575", "2020-11-22T07:15:58.000585", "2020-11-14T16:16:37.000596", "2020-10-10T10:16:45.000607", "2020-08-11T14:16:20.000617", "2020-06-26T17:16:45.000627", "2020-10-02T20:15:49.000637", "2021-02-01T04:16:28.000647", "2021-01-04T18:16:51.000658", "2021-02-15T12:16:37.000668", "2020-08-21T15:17:12.000678", "2020-11-30T05:16:19.000688", "2020-08-13T13:17:00.000698", "2020-11-02T22:15:30.000708", "2020-04-09T23:15:28.000719", "2021-01-31T07:16:35.000729", "2020-11-25T19:17:10.000739", "2021-02-04T04:15:32.000749", "2020-08-22T09:16:29.000774", "2020-12-22T16:16:09.000784", "2020-09-22T09:15:48.000794", "2021-02-15T15:16:00.000804", "2021-02-02T20:15:43.000813", "2021-03-20T01:15:53.000823", "2020-03-28T22:16:15.000833", "2020-07-30T20:16:58.000842", "2020-04-21T23:16:17.000852", "2020-08-24T15:15:20.000862", "2020-05-28T05:16:44.000871", "2021-04-18T11:16:12.000881", "2020-12-24T20:15:23.000891", "2020-05-12T01:16:17.000900", "2020-07-11T09:17:10.000910", "2020-08-09T06:16:39.000920", "2020-12-18T09:15:34.000930", "2020-06-23T04:16:25.000939", "2020-08-22T01:16:57.000949", "2020-09-25T16:16:53.000959", "2021-02-06T19:15:13.000968", "2020-10-03T09:15:31.000978", "2020-07-28T08:16:46.000988", "2021-02-05T11:16:30.000997", "2020-11-24T00:15:35.000007", "2020-05-24T20:15:43.000017", "2020-03-28T08:15:56.000027", "2020-09-09T15:16:59.000037", "2020-07-27T01:15:53.000046", "2021-01-30T20:16:32.000056", "2020-04-23T04:16:02.000066", "2020-10-26T03:16:37.000076", "2020-10-25T15:16:09.000085", "2021-03-25T16:15:41.000096", "2021-02-28T03:17:07.000106", "2020-04-25T09:15:28.000116", "2020-04-11T00:16:52.000125", "2020-04-13T17:16:49.000135", "2020-12-21T09:17:07.000145", "2021-02-11T03:17:13.000155", "2021-01-22T10:16:52.000164", "2020-09-25T23:15:17.000174", "2020-04-25T07:15:21.000184", "2020-05-22T08:16:16.000194", "2020-08-19T19:15:53.000203", "2020-11-04T23:15:19.000213", "2020-04-18T22:16:58.000222", "2020-06-30T07:17:02.000232", "2020-09-01T23:16:52.000242", "2021-01-29T00:16:31.000251", "2020-06-02T01:16:58.000261", "2020-04-05T09:15:44.000271", "2021-01-20T00:16:41.000282", "2021-02-06T15:17:04.000292", "2020-05-28T04:15:17.000302", "2020-10-19T12:16:11.000312", "2020-04-28T04:15:30.000321", "2020-11-23T16:16:52.000331", "2020-07-13T10:16:04.000341", "2021-02-18T07:17:08.000350", "2020-10-21T13:16:03.000360", "2020-05-06T00:17:11.000370", "2020-07-22T14:15:31.000379", "2020-12-16T07:15:24.000389", "2021-03-19T21:16:02.000399", "2021-03-15T19:16:09.000409", "2021-02-10T22:17:11.000418", "2021-02-07T08:16:05.000428", "2020-12-28T18:16:20.000438", "2020-10-04T06:16:43.000448", "2021-02-23T21:15:16.000458", "2020-10-05T10:16:12.000467", "2020-08-06T08:17:01.000477", "2020-04-12T17:15:44.000544", "2020-11-11T23:15:53.000560", "2020-09-07T07:17:11.000572", "2020-09-14T21:15:24.000582", "2020-04-12T07:17:00.000592", "2020-03-29T09:17:07.000602", "2020-12-17T16:17:13.000612", "2020-06-05T17:16:52.000622", "2020-09-02T16:16:21.000632", "2020-09-29T11:17:09.000642", "2020-07-05T06:16:11.000652", "2020-09-19T19:15:23.000662", "2020-03-23T12:16:22.000671", "2020-11-18T14:17:07.000681", "2021-02-21T04:15:45.000691", "2020-06-23T17:16:31.000701", "2020-10-17T07:15:29.000711", "2020-11-20T23:15:47.000721", "2020-11-12T22:16:55.000730", "2021-01-12T19:15:58.000740", "2020-12-21T20:16:32.000749"
        };

        int count = countFuture(isos);
        System.out.println("Future count: " + count);
    }
}
