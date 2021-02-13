public class StudentAttendanceRecordI_551 {
    //You are given a string representing an attendance record for a student. The record only contains the following three characters:
    //'A' : Absent.
    //'L' : Late.
    //'P' : Present.
    //A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
    //
    //You need to return whether the student could be rewarded according to his attendance record.
    //
    //Example 1:
    //Input: "PPALLP"
    //Output: True
    //Example 2:
    //Input: "PPALLL"
    //Output: False
    // A <= 1 && L <= 2 遍历 时间复杂度O(N) 空间复杂度 O（1）
    public boolean checkRecord(String s) {
        int a = 0, l = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'A') a++;
            if(s.charAt(i) == 'L') l++;
        }
        return (a <= 1 && l <= 2);
    }

    //切割 时间复杂度O（1）  空间复杂度 O（1）
    public boolean checkRecord2(String s) {
        int a = s.split("A").length;
        int l = s.split("L").length;
        return (a <= 1 && l <= 2);
    }
}
