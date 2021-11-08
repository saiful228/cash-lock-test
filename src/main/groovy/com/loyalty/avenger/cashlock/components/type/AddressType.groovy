package com.loyalty.avenger.cashlock.components.type


class AddressType {

    String line1
    String line2
    String line3
    String city
    String province
    String postalCode

    @Override
    String toString() {
        toJson()
    }


    String toJson() {
        com.loyalty.avenger.cashlock.components.util.TestDataUtils.mapToJson(getMap())
    }

    Map getMap() {
        Map map = [line1: line1]

        if(line2 != null) {
            map["line2"] = line2
        }

        if(line3 != null) {
            map["line3"] = line3
        }

        map["city"] = city
        map["province"] = province
        map["postalCode"] = postalCode
        map
    }

    def setDefaultValues() {
        Map map = com.loyalty.avenger.cashlock.components.util.TestDataUtils.getRandomEnumValue(com.loyalty.avenger.cashlock.components.constant.ValidAddress)
        this.line1 = map.get("line1")
        if(map.get("line2") != null) {
            this.line2 = map.get("line2")
        }
        if(map.get("line3") != null) {
            this.line2 = map.get("line3")
        }
        this.city = map.get("city")
        this.province = map.get("province")
        this.postalCode = map.get("postalCode")
    }

}
