package com.loyalty.avenger.cashlock.components.type

import com.loyalty.avenger.cashlock.components.util.TestDataUtils


class AdditionalDetailsType {


    String houseHoldIncome
    Integer totalMembers


    @Override
    String toString() {
        toJson()
    }


    String toJson() {
        TestDataUtils.mapToJson(getMap())
    }

    Map getMap() {
        Map map = [:]
        if( houseHoldIncome != null) {
            map["houseHoldIncome"] = houseHoldIncome
        }
        if( totalMembers != null) {
            map["totalMembers"] = totalMembers
        }
        map
    }

    def setDefaultValues() {
        this.houseHoldIncome = "3"
        this.totalMembers = 3
    }

    def mapResponse(Map bodyMap) {
        try {
            this.houseHoldIncome = bodyMap.getAt("additionalDetails")?.getAt("householdIncome").toString()
            this.totalMembers = bodyMap.getAt("additionalDetails")?.getAt("totalMembers").toString().toInteger()
        }
        catch (Exception e) {}
    }

}
