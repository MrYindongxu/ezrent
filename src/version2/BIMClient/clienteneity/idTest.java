package version2.BIMClient.clienteneity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class idTest {
    //身份证号码的字符串
    private String idstr;
    // 省份
    private String province;
    // 城市
    private String city;
    // 省市地区
    private static String location;
    // 年份
    private int year;
    // 月份
    private int month;
    // 日期
    private int day;
    // 性别
    private String gender;
    // 出生日期
    private Date birthday;

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 通过最后一位验证身份证合法性
     *
     * @param idstr
     * @return
     */
    public boolean islegit(String idstr) {
        final int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
        final int[] vi = {1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};
        int[] ai = new int[18];
        int remaining = 0;
        String last = idstr.substring(17, idstr.length());

        if (idstr.length() == 18) {
            idstr = idstr.substring(0, 17);
        }
        if (idstr.length() == 17) {
            int sum = 0;
            for (int i = 0; i < 17; i++) {
                String k = idstr.substring(i, i + 1);
                ai[i] = Integer.parseInt(k);
            }

            for (int i = 0; i < 17; i++) {
                sum = sum + wi[i] * ai[i];
            }
            remaining = sum % 11;
        }
        return (last.equals(remaining == 2 ? "X" : String.valueOf(vi[remaining])));
    }


    public idTest() {
    }
    //判断合法性

    public boolean idLegit(String idstr) {
        if (islegit(idstr)) {
            // 获取地址
            CityMap cm = new CityMap();
            location = cm.getLocation(idstr);
            // 获取性别
            String sex = idstr.substring(16, 17);
            if (Integer.parseInt(sex) % 2 != 0) {
                this.gender = "男";
            } else {
                this.gender = "女";
            }
            // 获取出生日期
            String birthday = idstr.substring(6, 14);
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
                this.birthday = birthdate;
                GregorianCalendar currentDay = new GregorianCalendar();
                currentDay.setTime(birthdate);
                this.year = currentDay.get(Calendar.YEAR);
                this.month = currentDay.get(Calendar.MONTH) + 1;
                this.day = currentDay.get(Calendar.DAY_OF_MONTH);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(toString());
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "地区：" + location + ",性别：" + this.gender + ",出生日期："
                + this.year + "年" + this.month + "月" + this.day + "日";
    }
}

