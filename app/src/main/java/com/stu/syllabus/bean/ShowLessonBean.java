package com.stu.syllabus.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * yuan
 * 2019/12/3
 **/
public class ShowLessonBean {
    /**
     * name : [PED1060A]网球
     * id : 112857
     * teacher : 王家君
     * room : 网球场
     * duration : 3 -18
     * days : {"w0":"None","w1":"None","w2":"12","w3":"None","w4":"None","w5":"None","w6":"None"}
     * credit : 0.0
     */

    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public String id;
    @SerializedName("teacher")
    public String teacher;
    @SerializedName("room")
    public String room;
    @SerializedName("duration")
    public String duration;
    @SerializedName("days")
    public DaysBean days;
    @SerializedName("credit")
    public String credit;

    private int bgColor;

    public ShowLessonBean(String name, String id, String teacher, String room, String duration, DaysBean days, String credit) {
        super();
        this.name = name;
        this.id = id;
        this.teacher = teacher;
        this.room = room;
        this.duration = duration;
        this.days = days;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public DaysBean getDays() {
        return days;
    }

    public void setDays(DaysBean days) {
        this.days = days;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public static class DaysBean {
        /**
         * w0 : None    //周日
         * w1 : None
         * w2 : 12
         * w3 : None
         * w4 : None
         * w5 : None
         * w6 : None
         */

        @SerializedName("w0")
        public String w0;
        @SerializedName("w1")
        public String w1;
        @SerializedName("w2")
        public String w2;
        @SerializedName("w3")
        public String w3;
        @SerializedName("w4")
        public String w4;
        @SerializedName("w5")
        public String w5;
        @SerializedName("w6")
        public String w6;

        public String getW0() {
            return w0;
        }

        public void setW0(String w0) {
            this.w0 = w0;
        }

        public String getW1() {
            return w1;
        }

        public void setW1(String w1) {
            this.w1 = w1;
        }

        public String getW2() {
            return w2;
        }

        public void setW2(String w2) {
            this.w2 = w2;
        }

        public String getW3() {
            return w3;
        }

        public void setW3(String w3) {
            this.w3 = w3;
        }

        public String getW4() {
            return w4;
        }

        public void setW4(String w4) {
            this.w4 = w4;
        }

        public String getW5() {
            return w5;
        }

        public void setW5(String w5) {
            this.w5 = w5;
        }

        public String getW6() {
            return w6;
        }

        public void setW6(String w6) {
            this.w6 = w6;
        }

        @Override
        public String toString() {
            StringBuffer str = new StringBuffer();
            if (getW0()!= null) {
                str.append("周日 " + getW0() + "\n");
            }
            if (getW1()!= null) {
                str.append("周一 " + getW1() + "\n");
            }
            if (getW2()!= null) {
                str.append("周二 " + getW2() + "\n");
            }
            if (getW3()!= null) {
                str.append("周三 " + getW3() + "\n");
            }
            if (getW4()!= null) {
                str.append("周四 " + getW4() + "\n");
            }
            if (getW5()!= null) {
                str.append("周五 " + getW5() + "\n");
            }
            if (getW6()!= null) {
                str.append("周六 " + getW6() + "\n");
            }
            return str.toString();
        }
    }
}
