import net.sf.json.JSONArray;

public class JsonRepeat {

    public static void Repeat(JSONArray jsonArray) {
        /**
         * json去重
         */
        JSONArray array = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            if (i == 0) {
                array.add(jsonArray.getJSONObject(i));
            } else {
                boolean flag = false; //用来判断是否存在重复的元素，false-没有 true-有
                Object serial = jsonArray.getJSONObject(i).get("serial");
                for (int j = 0; j < array.size(); j++) {
                    Object temp = array.getJSONObject(j).get("serial");
                    if (serial.equals(temp)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    array.add(jsonArray.getJSONObject(i));
                }
            }
        }
        System.out.println("初始的jsonStr：" + jsonArray);

        System.out.println("去重后的jsonStr：" + array);
    }

    public static void main(String[] args) {
        String str = "[{\n" +
                "    \"name\": \"张三\",\n" +
                "    \"serial\": \"0001\"\n" +
                "  }, {\n" +
                "    \"name\": \"李四\",\n" +
                "    \"serial\": \"0002\"\n" +
                "  }, {\n" +
                "    \"name\": \"王五\",\n" +
                "    \"serial\": \"0003\"\n" +
                "  }, {\n" +
                "    \"name\": \"王五2\",\n" +
                "    \"serial\": \"0003\"\n" +
                "  }, {\n" +
                "    \"name\": \"赵四\",\n" +
                "    \"serial\": \"0004\"\n" +
                "  }, {\n" +
                "    \"name\": \"小明\",\n" +
                "    \"serial\": \"005\"\n" +
                "  }, {\n" +
                "    \"name\": \"小张\",\n" +
                "    \"serial\": \"006\"\n" +
                "  }, {\n" +
                "    \"name\": \"小李\",\n" +
                "    \"serial\": \"006\"\n" +
                "  }, {\n" +
                "    \"name\": \"小李2\",\n" +
                "    \"serial\": \"006\"\n" +
                "  }, {\n" +
                "    \"name\": \"赵四2\",\n" +
                "    \"serial\": \"0004\"\n" +
                "  }]";
        JSONArray jsonArray = JSONArray.fromObject(str);
        Repeat(jsonArray);

    }
}
