package com.tpb.ekyc.screen;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyToStringLiveBank {

    public static String getValueMoneyString(String money){
        if(money==null || money.isEmpty()) return "";

        money = money.replaceAll(",","").trim();
        return numberToTextVN(BigDecimal.valueOf(Long.parseLong(money)));
    }


    public static String numberToTextVN(BigDecimal total) {
        try {
            String rs = "";
            total = total.setScale(0, RoundingMode.HALF_UP);
            String[] ch = { "không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín" };
            String[] rch = { "lẻ", "mốt", "", "", "", "lăm" };
            String[] u = { "", "mươi", "trăm", "ngàn", "", "", "triệu", "", "", "tỷ", "", "", "ngàn", "", "", "triệu" };
            String nstr = total.toString();
            int[] n = new int[nstr.length()];
            int len = n.length;

            for (int i = 0; i < len; i++) {
                n[len - 1 - i] = Character.getNumericValue(nstr.charAt(i));
            }

            for (int i = len - 1; i >= 0; i--) {
                if (i % 3 == 2) {
                    if (n[i] == 0 && n[i - 1] == 0 && n[i - 2] == 0) continue;
                } else if (i % 3 == 1) {
                    if (n[i] == 0) {
                        if (n[i - 1] == 0) continue;
                        else {
                            rs += " " + rch[n[i]];
                            continue;
                        }
                    }
                    if (n[i] == 1) {
                        rs += " mười";
                        continue;
                    }
                } else if (i != len - 1) {
                    if (n[i] == 0) {
                        if (i + 2 <= len - 1 && n[i + 2] == 0 && n[i + 1] == 0) continue;
                        rs += " " + (i % 3 == 0 ? u[i] : u[i % 3]);
                        continue;
                    }
                    if (n[i] == 1) {
                        rs += " " + ((n[i + 1] == 1 || n[i + 1] == 0) ? ch[n[i]] : rch[n[i]]);
                        rs += " " + (i % 3 == 0 ? u[i] : u[i % 3]);
                        continue;
                    }
                    if (n[i] == 5) {
                        if (n[i + 1] != 0) {
                            rs += " " + rch[n[i]];
                            rs += " " + (i % 3 == 0 ? u[i] : u[i % 3]);
                            continue;
                        }
                    }
                }
                rs += (rs.equals("") ? " " : " ") + ch[n[i]];
                rs += " " + (i % 3 == 0 ? u[i] : u[i % 3]);
            }

            if (rs.charAt(rs.length() - 1) != ' ')
                rs += " đồng";
            else
                rs += "đồng";

            if (rs.length() > 2) {
                String rs1 = rs.substring(0, 2);
                rs1 = rs1.toUpperCase();
                rs = rs.substring(2);
                rs = rs1 + rs;
            }

            return rs.trim().replace("lẻ,", "lẻ").replace("mươi,", "mươi").replace("trăm,", "trăm").replace("mười,", "mười");
        } catch (Exception ex) {
//            Log.i("NumberToTextVN", "Exception: " + ex);
            return "";
        }
    }

}

