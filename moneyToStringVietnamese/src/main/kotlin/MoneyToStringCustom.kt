

fun numberToString(number: Long): String {
    var value = ""
    val i = number
    value = when (i) {
        in 1..10 -> getDigitString(i)

        in 11..19 -> {
            val onesDigit = i % 10

            if(onesDigit.toInt()==0) "mười " else "mười " + getDigitString(onesDigit)
        }

        in 20..99 -> {
            val tensDigit = i / 10
            val onesDigit = i % 10
            if (onesDigit.toInt() == 1) {
                getDigitString(tensDigit) + " mươi mốt"
            } else if(onesDigit.toInt() == 0){
                getDigitString(tensDigit) + " mươi "
            } else if(onesDigit.toInt() == 4 && onesDigit.toInt() > 1){
                getDigitString(tensDigit) + " mươi tư"
            }
            else {
                if(onesDigit.toInt() == 5 && onesDigit.toInt() > 1) getDigitString(tensDigit) + " mươi lăm" else getDigitString(tensDigit) + " mươi " + getDigitString(onesDigit)
            }
        }

        in 100..999 -> {
            val hundredsDigit = i / 100
            val tensDigit = (i % 100) / 10
            val onesDigit = i % 10
            val hundredsString = getDigitString(hundredsDigit) + " trăm"
            var tensString = if (tensDigit.toInt() == 0) " lẻ" else {
                if (tensDigit.toInt() == 1) " mười"  else " " + getDigitString(tensDigit) + " mươi"
            }
            if (tensDigit.toInt() == 0 && onesDigit.toInt() == 0) {
                 tensString = ""
            }
            val onesString = if (onesDigit.toInt() == 0) "" else {
                if (tensDigit >= 2 && onesDigit.toInt() == 1) " mốt" else if (tensDigit >= 2 && onesDigit.toInt() == 4) " tư" else if(tensDigit >= 1 && onesDigit.toInt() == 5) " lăm" else if(onesDigit.toInt() == 0) " lẻ " + getDigitString(onesDigit) else " " + getDigitString(onesDigit)
            }
            hundredsString + tensString + onesString
        }

        in 1000..999_999 -> {
            val thousands = number / 1000
            val rest = number % 1000
            val thousandsString = numberToString(thousands)
            val restString = if (rest >= 100) numberToString(rest) else if (rest in 1..9) " lẻ "+numberToString(rest) else if (rest in 10..99) " không trăm "+numberToString(rest) else ""

            "$thousandsString nghìn $restString"
        }
        in 1_000_000..999_999_999 -> {
            val millions = number / 1_000_000
            val rest = number % 1000000
            val millionsString = numberToString(millions)
            val restString = if (rest > 0) numberToString(rest) else ""
            "$millionsString triệu $restString"
        }

        in 1_000_000_000..999_999_999_999 -> {
            val billions = number / 1_000_000_000
            val rest = number % 1_000_000_000
            val billionsString = numberToString(billions)
            val restString = if (rest > 0) numberToString(rest) else ""
            "$billionsString tỷ $restString"
        }
        in 1_000_000_000_000..999_999_999_999_999 -> {
            val thousBillions = number / 1_000_000_000_000
            val rest = number % 1_000_000_000_000
            val thousBiString = numberToString(thousBillions)
            val restString = if (rest > 0) numberToString(rest) else ""
            "$thousBiString nghìn $restString"
        }

        else -> ""
    }

    return value
}

fun main() {

    val i=9505434
    var rs = numberToString(i.toLong())+" đồng"
    rs = rs.replace("  "," ")
    println(rs)
}

fun getDigitString(digit: Long): String {
    return when (digit.toInt()) {
        0 -> "không"
        1 -> "một"
        2 -> "hai"
        3 -> "ba"
        4 -> "bốn"
        5 -> "năm"
        6 -> "sáu"
        7 -> "bảy"
        8 -> "tám"
        9 -> "chín"
        10 -> "mười"
        else -> ""
    }
}

