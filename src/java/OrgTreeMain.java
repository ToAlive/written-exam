import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrgTreeMain {

    /**
     * 把下面给出的扁平化json数据用递归的方式改写成组织树的形式
     * jsonStr:给定的json串
     * orgJsonArray：一个新的JSONArray对象，用来保存重组后的机构结构树
     */
    public static JSONArray orgTree(Object jsonStr, JSONArray orgJsonArray) {
        if (jsonStr instanceof JSONArray) {
            JSONArray array = (JSONArray) jsonStr;
            for (int i = 0; i < array.size(); i++) {
                orgTree(array.getJSONObject(i), orgJsonArray);
            }
        } else {
            if (jsonStr instanceof JSONObject) {
                JSONObject orgJsonObj = (JSONObject) jsonStr;
                String parent = orgJsonObj.getString("parent");
                if ("".equals(parent)) { //添加顶级机构
                    orgJsonArray.add(orgJsonObj);
                } else { //添加下属机构
                    for (int i = 0; i < orgJsonArray.size(); i++) {
                        JSONObject jsonObject = orgJsonArray.getJSONObject(i);
                        addSub(jsonObject, parent, orgJsonObj);
                    }
                }
            }
        }
        return orgJsonArray;
    }

    /**
     * 添加子机构节点
     * @param orgJsonObject 添加子机构节点的目标对象
     * @param parent 当前正在做比较的json机构串的父id
     * @param orgJsonObj 当前正在做比较的json机构串
     */
    public static void addSub(JSONObject orgJsonObject, String parent, JSONObject orgJsonObj) {
        String tempCode = orgJsonObject.getString("code");
        if (parent.equals(tempCode)) {
            if (!orgJsonObject.containsKey("sub")) {
                JSONArray array = new JSONArray();
                array.add(orgJsonObj);
                orgJsonObject.accumulate("sub", array);
            } else {
                orgJsonObject.getJSONArray("sub").add(orgJsonObj);
            }
        } else {
            if (orgJsonObject.containsKey("sub")) {
                JSONArray subArray = orgJsonObject.getJSONArray("sub");
                for (int i = 0; i < subArray.size(); i++) {
                    addSub(subArray.getJSONObject(i), parent, orgJsonObj);
                }
            }
        }
    }

    public static void main(String[] args) {
        String str = "  [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"name\": \"中国\",\n" +
                "      \"code\": \"110\",\n" +
                "      \"parent\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2\",\n" +
                "      \"name\": \"北京市\",\n" +
                "      \"code\": \"110000\",\n" +
                "      \"parent\": \"110\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"3\",\n" +
                "      \"name\": \"河北省\",\n" +
                "      \"code\": \"130000\",\n" +
                "      \"parent\": \"110\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"4\",\n" +
                "      \"name\": \"四川省\",\n" +
                "      \"code\": \"510000\",\n" +
                "      \"parent\": \"110\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5\",\n" +
                "      \"name\": \"石家庄市\",\n" +
                "      \"code\": \"130001\",\n" +
                "      \"parent\": \"130000\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6\",\n" +
                "      \"name\": \"唐山市\",\n" +
                "      \"code\": \"130002\",\n" +
                "      \"parent\": \"130000\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"7\",\n" +
                "      \"name\": \"邢台市\",\n" +
                "      \"code\": \"130003\",\n" +
                "      \"parent\": \"130000\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"8\",\n" +
                "      \"name\": \"成都市\",\n" +
                "      \"code\": \"510001\",\n" +
                "      \"parent\": \"510000\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"9\",\n" +
                "      \"name\": \"简阳市\",\n" +
                "      \"code\": \"510002\",\n" +
                "      \"parent\": \"510000\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"10\",\n" +
                "      \"name\": \"武侯区\",\n" +
                "      \"code\": \"51000101\",\n" +
                "      \"parent\": \"510001\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"11\",\n" +
                "      \"name\": \"金牛区\",\n" +
                "      \"code\": \"51000102\",\n" +
                "      \"parent\": \"510001\"\n" +
                "    }\n" +
                "  ]";
        JSONArray jsonStr = JSONArray.fromObject(str);
        JSONArray orgJsonArray = new JSONArray();
        orgJsonArray = orgTree(jsonStr, orgJsonArray);
        System.out.println(orgJsonArray);
    }
}
