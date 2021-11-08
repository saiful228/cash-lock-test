package com.loyalty.avenger.cashlock.components.type




class AttributesType {
    String name
    List<String> values = new ArrayList<String>()

    @Override
    String toString () {
        toJson()
    }

    Map getAttributeMap() {
        Map attributeMap = [name: name]
        attributeMap["values"] = values
        attributeMap
    }

    String toJson() {
        com.loyalty.avenger.cashlock.components.util.TestDataUtils.mapToJson(getAttributeMap())
    }

    def addValue(String value) {
        values.add(value)
    }

}
