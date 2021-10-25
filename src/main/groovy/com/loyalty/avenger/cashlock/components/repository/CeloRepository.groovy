package com.loyalty.avenger.cashlock.components.repository


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

/**
 *
 */

@Repository
class CeloRepository {

    @Autowired
    @Qualifier("jdbcCelo")
    JdbcTemplate jdbcTemplate

    private final static Logger logger = LoggerFactory.getLogger(this)


    List<String> getListOfGhostCollectors() {
        String query = "select card.card_id\n" +
                "from cpm.cpm_member mem\n" +
                "join cpm.cpm_customer cust\n" +
                "on mem.customer_id= cust.customer_id\n" +
                "join cpm.cpm_member_card card\n" +
                "on mem.member_id = card.member_id\n" +
                "left join cpm.cpm_card_print ccp on ccp.card_number = card.card_id\n" +
                "left join cpm.cpm_acc_summary acc on mem.member_id = acc.member_id\n" +
                "left join cpm.cpm_grp_acc_summary gac on gac.group_id = mem.group_id\n" +
                "where mem.status in (${com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue()}, ${com.loyalty.collector.components.constant.MembershipStatus.DORMANT.getValue()})\n" +
                "and mem.is_ghost = 1\n" +
                "and cust.email not in ('abc@abc.com', 'test@test.com', 'hellothere@gmail.com', 'CoUZM@gmai', 'CoUZM@gmail.com', 'abc^&amp;@#@loyalty.com', 'petersung2288822@gmail.com', 'saiful.islam30@gmail.com', 'lvukadin@loyalty.com', 'awcawec@aedcace.cas', 'jbulatao@loyalty.com', 'abc@loyalty.com', '15ekha@loyalty.com', '106046test@test.com', 'test@loyalty.com')\n" +
                "and cust.email like '%_@__%.___%'\n" +
                "limit 100;"
        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("card_id").toString())
        }
        collectors
    }

    List<String> getCollectorListByEmail(String email) {
        String query = "select mc.card_id \n" +
                "from cpm.cpm_member mem\n" +
                "join cpm.cpm_member_card mc\n" +
                "on mc.member_id = mem.member_id\n" +
                "join cpm.cpm_customer cu\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "where cu.email = '$email'"
        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("card_id").toString())
        }
        collectors
    }

    List<String> getRandomCollectorsListByStatusNoEmailFilter(String status = com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue(), Integer totalRecords = 400) {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "join cpm.cpm_customer cu\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "JOIN CPM.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_mbr_earning_pref mep\n" +
                "on mep.member_id = mem.member_id\n" +
                "join cpm.cpm_mbr_opt_in_pref f\n" +
                "on f.member_id = mem.member_id\n" +
                "and mem.`status` = $status\n" +
                "and mem.is_ghost = '0' \n" +
                "and f.category = 4 \n" +
                "and f.sub_category = 36 \n" +
                "and mem.test_member = 0\n" +
                "and mep.earning_ratio != 100\n" +
                "and mem.membership_type != 829\n" +
                "and mem.member_id < 90000000000\n" +
                "and mep.is_active = 1\n" +
                "limit $totalRecords"
        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("member_id").toString())
        }
        collectors
    }

    List<String> getRandomGhostDormantCollectorsListByStatus(String status = com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue(), Integer totalRecords = 400) {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "join cpm.cpm_customer cu\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "JOIN CPM.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_mbr_earning_pref mep\n" +
                "on mep.member_id = mem.member_id\n" +
                "join cpm.cpm_mbr_opt_in_pref f\n" +
                "on f.member_id = mem.member_id\n" +
                "where mem.`status` = $status\n" +
                "and mem.is_ghost = '1' \n" +
                "and f.category = 4 \n" +
                "and f.sub_category = 36 \n" +
                "and mem.test_member = 0\n" +
                "and mep.earning_ratio != 100\n" +
                "and mem.membership_type != 829\n" +
                "and mem.member_id < 90000000000\n" +
//                "and cu.email not in (" +
//                "'abc@abc.com', " +
//                "'test@test.com', " +
//                "'hellothere@gmail.com', " +
//                "'CoUZM@gmai', " +
//                "'CoUZM@gmail.com', " +
//                "'abc^&amp;@#@loyalty.com', " +
//                "'petersung2288822@gmail.com', " +
//                "'saiful.islam30@gmail.com', " +
//                "'lvukadin@loyalty.com', " +
//                "'awcawec@aedcace.cas', " +
//                "'jbulatao@loyalty.com', " +
//                "'abc@loyalty.com', " +
//                "'15ekha@loyalty.com', " +
//                "'test@loyalty.com')\n" +
//                "and cu.email like '%_@__%.___%'\n" +
                "and mep.is_active = 1\n" +
                "limit $totalRecords"
        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("member_id").toString())
        }
        collectors
    }


    List<String> getRandomCollectorsListByStatus(String status = com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue(), def totalRecords = 400) {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "join cpm.cpm_customer cu\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "JOIN CPM.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_mbr_earning_pref mep\n" +
                "on mep.member_id = mem.member_id\n" +
                "join cpm.cpm_mbr_opt_in_pref f\n" +
                "on f.member_id = mem.member_id\n" +
                "where mem.`status` = $status\n" +
                "and mem.is_ghost = '0' \n" +
                "and f.category = 4 \n" +
                "and f.sub_category = 36 \n" +
                "and mem.test_member = 0\n" +
                "and mep.earning_ratio != 100\n" +
                "and mem.membership_type != 829\n" +
                "and mem.member_id < 90000000000\n" +
                "and cu.email not in (" +
                "'abc@abc.com', " +
                "'test@test.com', " +
                "'hellothere@gmail.com', " +
                "'CoUZM@gmai', " +
                "'CoUZM@gmail.com', " +
                "'abc^&amp;@#@loyalty.com', " +
                "'petersung2288822@gmail.com', " +
                "'saiful.islam30@gmail.com', " +
                "'lvukadin@loyalty.com', " +
                "'awcawec@aedcace.cas', " +
                "'jbulatao@loyalty.com', " +
                "'abc@loyalty.com', " +
                "'15ekha@loyalty.com', " +
                "'106046test@test.com', " +
                "'test@loyalty.com')\n" +
                "and cu.email like '%_@__%.___%'\n" +
                "and mep.is_active = 1\n" +
                "limit $totalRecords"
                List<String> collectors = new ArrayList<String>()
                def result = jdbcTemplate.queryForList(query)
                result.each {
                      collectors.add(it.get("member_id").toString())
                }
        collectors
    }

    List<String> getRandomFrenchCollectorsListByStatus(String status = com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue(), Integer totalRecords = 400) {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "join cpm.cpm_customer cu\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "JOIN CPM.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_mbr_earning_pref mep\n" +
                "on mep.member_id = mem.member_id\n" +
                "join cpm.cpm_mbr_opt_in_pref f\n" +
                "on f.member_id = mem.member_id\n" +
                "and mem.`status` = $status\n" +
                "and mem.is_ghost = '0' \n" +
                "and f.category = 4 \n" +
                "and f.sub_category = 37 \n" +
                "and mem.test_member = 0\n" +
                "and mep.earning_ratio != 100\n" +
                "and mem.membership_type != 829\n" +
                "and mem.member_id < 90000000000\n" +
                "and cu.email not in (" +
                "'abc@abc.com', " +
                "'test@test.com', " +
                "'hellothere@gmail.com', " +
                "'CoUZM@gmai', " +
                "'CoUZM@gmail.com', " +
                "'abc^&amp;@#@loyalty.com', " +
                "'petersung2288822@gmail.com', " +
                "'saiful.islam30@gmail.com', " +
                "'lvukadin@loyalty.com', " +
                "'awcawec@aedcace.cas', " +
                "'jbulatao@loyalty.com', " +
                "'jhellinga@loyalty.com', " +
                "'abc@loyalty.com', " +
                "'15ekha@loyalty.com', " +
                "'test@loyalty.com')\n" +
                "and cu.email like '%_@__%.___%'\n" +
                "and mep.is_active = 1\n" +
                "limit $totalRecords"
        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("member_id").toString())
        }
        collectors
    }

    List<String> getRandomGhostCollectorsList(Integer totalRecords = 1000) {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "JOIN CPM.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_mbr_earning_pref mep\n" +
                "on mep.member_id = mem.member_id\n" +
                "and mem.`status` = ${com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue()} \n" +
                "and mem.is_ghost = '1' \n" +
                "and mem.test_member = 0\n" +
                "and mep.earning_ratio != 100\n" +
                "limit $totalRecords"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("member_id").toString())
        }
        collectors
    }


    List<String> getRandomBusinessCollectorsList() {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "join cpm.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_group gr \n" +
                "on gr.group_id=mem.group_id\n" +
                "join cpm.cpm_company com\n" +
                "on com.company_id=gr.company_id\n" +
                "where mem.membership_type in ('829', '830')\n" +
                "and mem.`status` = ${com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue()}\n" +
                "and mem.is_ghost = '0'\n" +
                "limit 1000"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("member_id").toString())
        }
        collectors
    }

    List<String> getRandomEmployeeCollectorsList(String status = com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue()) {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "JOIN CPM.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_mbr_earning_pref mep\n" +
                "on mep.member_id = mem.member_id\n" +
                "and mem.`status` = $status\n" +
                "and mem.is_ghost = '0' \n" +
                "and mem.test_member = 0\n" +
                "and is_employee = '1'\n" +
                "and mep.earning_ratio != 100\n" +
                "limit 1000"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("member_id").toString())
        }
        collectors
    }

    List<String> getRandomCollectorsListByStatusRelaxed(String status = com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue(), def totalRecords = 400) {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "join cpm.cpm_customer cu\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "JOIN CPM.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_mbr_earning_pref mep\n" +
                "on mep.member_id = mem.member_id\n" +
                "join cpm.cpm_mbr_opt_in_pref f\n" +
                "on f.member_id = mem.member_id\n" +
                "and mem.`status` = $status\n" +
                "and mem.is_ghost = '0' \n" +
                "and f.category = 4 \n" +
                "and f.sub_category = 36 \n" +
                "and mem.test_member = 0\n" +
                "and mep.earning_ratio != 100\n" +
                "and mem.membership_type != 829\n" +
                "and mem.member_id < 90000000000\n" +
                "and cu.email not in (" +
                "'abc@abc.com', " +
                "'test@test.com', " +
                "'hellothere@gmail.com', " +
                "'CoUZM@gmai', " +
                "'CoUZM@gmail.com', " +
                "'abc^&amp;@#@loyalty.com', " +
                "'lvukadin@loyalty.com', " +
                "'awcawec@aedcace.cas', " +
                "'jbulatao@loyalty.com', " +
                "'jhellinga@loyalty.com', " +
                "'abc@loyalty.com', " +
                "'15ekha@loyalty.com', " +
                "'test@loyalty.com')\n" +
                "and cu.email like '%_@__%.___%'\n" +

                "and mep.is_active = 1\n" +
                "limit $totalRecords"
        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("member_id").toString())
        }
        collectors
    }


    String getRandomActiveIssuerByProviderCode(String providerCode) {
        String query = "select iss.issuer_code\n" +
                "from cpm.cpm_issuer iss\n" +
                "join cpm.cpm_issuance_offer io\n" +
                "on iss.issuer_id = io.issuer_id\n" +
                "join cpm.cpm_issuer_relationship ir\n" +
                "on ir.issuer_id = iss.issuer_id\n" +
                "join cpm.cpm_provider pr\n" +
                "on ir.provider_id = pr.provider_id\n" +
                "where iss.is_active = 1\n" +
                "and iss.effective_begin_date <= now()\n" +
                "and iss.effective_end_date > now()\n" +
                "and io.is_active = 1\n" +
                "and io.effective_begin_date <= now()\n" +
                "and io.offer_end_date >= now()\n" +
                "and iss.issuer_type != '553'\n" +
                "and iss.issuer_type != '3189'\n" +
                "and iss.issuer_type != '3221'\n" +
                "and io.millage_amt > 50\n" +
                "and io.millage_type = 580\n" +
                "and pr.provider_code = 'PPAM'"
        com.loyalty.collector.components.util.TestDataUtils.getRandomRecordFromList(
                jdbcTemplate.queryForList(query)
        ).getAt("issuer_code").toString()
    }

    String getRandomActiveIssuer() {
        String query = "select iss.issuer_code\n" +
                "from cpm.cpm_issuer iss\n" +
                "join cpm.cpm_issuance_offer io\n" +
                "on iss.issuer_id = io.issuer_id\n" +
                "where iss.is_active = 1\n" +
                "and iss.effective_begin_date <= now()\n" +
                "and iss.effective_end_date > now()\n" +
                "and io.is_active = 1\n" +
                "and io.effective_begin_date <= now()\n" +
                "and io.offer_end_date >= now()\n" +
                "and iss.issuer_type not in ('553', '551', '552', '555', '3189', '3221')\n" +
                "and io.millage_amt > 50\n" +
                "and io.millage_type = 580"

        com.loyalty.collector.components.util.TestDataUtils.getRandomRecordFromList(
                jdbcTemplate.queryForList(query)
        ).getAt("issuer_code").toString()
    }

    String getSurvivalMember(String victimCollector) {
        String query = "select mem.survival_member_id from cpm.cpm_member mem where mem.member_id = '$victimCollector'"

        jdbcTemplate.queryForObject(query, String.class)
    }

    String getMemberCardId(String memberId) {
        String query = "select mc.card_id from cpm.cpm_member_card mc where mc.member_id = '$memberId';"

        jdbcTemplate.queryForObject(query, String.class)
    }

    List<String> getDupeEmailCollectorsList(String status, Integer totalRecords =  20) {
        String query = "select mc.card_id \n" +
                "from cpm.cpm_member_card mc\n" +
                "join cpm.cpm_member mem\n" +
                "on mc.member_id = mem.member_id\n" +
                "join (\n" +
                "select cu.customer_id, COUNT(*) \n" +
                "from cpm.cpm_customer cu \n" +
                "where cu.email_status = ${status.toInteger()}\n" +
                "group by cu.email\n" +
                "having count(*) > 1 and count(*) < 1000\n" +
                "limit $totalRecords ) qu\n" +
                "on qu.customer_id = mem.customer_id\n" +
                "where mem.status in (${com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue()}, ${com.loyalty.collector.components.constant.MembershipStatus.DORMANT.getValue()})"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("card_id").toString())
        }
        collectors
    }

    List<String> getNonDupeEmailCollectorsList(String status, Integer totalRecords =  20) {
        String query = "select mc.card_id \n" +
                "from cpm.cpm_member_card mc\n" +
                "join cpm.cpm_member mem\n" +
                "on mc.member_id = mem.member_id\n" +
                "join (\n" +
                "select cu.customer_id, COUNT(*) \n" +
                "from cpm.cpm_customer cu \n" +
                "where cu.email_status = ${status.toInteger()}\n" +
                "group by cu.email\n" +
                "having count(*) = 1\n" +
                "limit $totalRecords ) qu\n" +
                "on qu.customer_id = mem.customer_id\n" +
                "where mem.status in (${com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue()}, ${com.loyalty.collector.components.constant.MembershipStatus.DORMANT.getValue()})"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("card_id").toString())
        }
        collectors
    }

    List<String> getNullEmailCollectorsList(Integer totalRecords =  100) {
        String query = "select mc.card_id \n" +
                "from cpm.cpm_customer cu\n" +
                "join cpm.cpm_member mem\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "join cpm.cpm_member_card mc \n" +
                "on mem.member_id = mc.member_id\n" +
                "where cu.email is null\n" +
                "limit $totalRecords"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("card_id").toString())
        }
        collectors
    }


    List<String> getVerifiedEmailCollectorsList(Integer totalRecords =  100) {
        getEmailCollectorsListByVerifiedStatus(true, totalRecords)
    }

    List<String> getNotVerifiedEmailCollectorsList(Integer totalRecords =  100) {
        getEmailCollectorsListByVerifiedStatus(false, totalRecords)
    }

    List<String> getEmailCollectorsListByVerifiedStatus(Boolean status, Integer totalRecords =  100) {
        String query = "select mc.card_id \n" +
                "from cpm.cpm_customer cu\n" +
                "join cpm.cpm_member mem\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "join cpm.cpm_member_card mc \n" +
                "on mem.member_id = mc.member_id\n" +
                "where cu.email_verified_flag = $status\n" +
                "and cu.email not in (" +
                "'abc@abc.com', " +
                "'test@test.com', " +
                "'hellothere@gmail.com', " +
                "'CoUZM@gmai', " +
                "'CoUZM@gmail.com', " +
                "'abc^&amp;@#@loyalty.com', " +
                "'lvukadin@loyalty.com', " +
                "'awcawec@aedcace.cas', " +
                "'jbulatao@loyalty.com', " +
                "'jhellinga@loyalty.com', " +
                "'abc@loyalty.com', " +
                "'15ekha@loyalty.com', " +
                "'test@loyalty.com')\n" +
                "and cu.email like '%_@__%.___%'\n" +
                "limit $totalRecords\n" +
                ";"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("card_id").toString())
        }
        collectors
    }

    List<String> getSecureEmailCollectorsList(Integer totalRecords =  100) {
        getEmailCollectorsListBySecureStatus(true, totalRecords)
    }

    List<String> getNotSecureEmailCollectorsList(Integer totalRecords =  100) {
        getEmailCollectorsListBySecureStatus(false, totalRecords)
    }


    List<String> getEmailCollectorsListBySecureStatus(Boolean status, Integer totalRecords =  100) {
        String query = "select mc.card_id \n" +
                "from cpm.cpm_customer cu\n" +
                "join cpm.cpm_member mem\n" +
                "on mem.customer_id = cu.customer_id\n" +
                "join cpm.cpm_member_card mc \n" +
                "on mem.member_id = mc.member_id\n" +
                "where cu.sec_flag = $status\n" +
                "and cu.email not in (" +
                "'abc@abc.com', " +
                "'test@test.com', " +
                "'hellothere@gmail.com', " +
                "'CoUZM@gmai', " +
                "'CoUZM@gmail.com', " +
                "'abc^&amp;@#@loyalty.com', " +
                "'lvukadin@loyalty.com', " +
                "'awcawec@aedcace.cas', " +
                "'jbulatao@loyalty.com', " +
                "'jhellinga@loyalty.com', " +
                "'abc@loyalty.com', " +
                "'15ekha@loyalty.com', " +
                "'test@loyalty.com')\n" +
                "and cu.email like '%_@__%.___%'\n" +
                "limit $totalRecords\n" +
                ";"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("card_id").toString())
        }
        collectors
    }



    List<String> getRandomVictimCollectorsList(Integer totalRecords =  100) {
        String query = "select mem.member_id\n" +
                "from cpm.cpm_member mem\n" +
                "JOIN CPM.cpm_member_card mc\n" +
                "ON mc.member_id=mem.member_id\n" +
                "join cpm.cpm_mbr_earning_pref mep\n" +
                "on mep.member_id = mem.member_id\n" +
                "join cpm.cpm_member mem1\n" +
                "on mem.survival_member_id = mem1.member_id\n" +
                "and mem.`status` = '616'\n" +
                "and mem.is_ghost = 0\n" +
                "and mem.is_employee = 0\n" +
                "and mem.test_member = 0\n" +
                "and mep.earning_ratio != 100\n" +
                "and mem1.`status` = '31'\n" +
                "and mem1.is_ghost = 0\n" +
                "and mem1.is_employee = 0\n" +
                "and mem1.test_member = 0\n" +
                "limit ${totalRecords}"

        List<String> collectors = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            collectors.add(it.get("member_id").toString())
        }
        collectors
    }


    List<String> getProviderListByIssuer(String issuerCode) {
        String query = "select distinct pr.provider_code from cpm.cpm_provider pr\n" +
                "join cpm.cpm_issuer_relationship ir\n" +
                "join cpm.cpm_issuer iss\n" +
                "on ir.issuer_id = iss.issuer_id\n" +
                "where pr.is_active=1\n" +
                "and ir.is_active = 1\n" +
                "and iss.is_active = 1\n" +
                "and iss.issuer_code = '$issuerCode'"

        List<String> providers = new ArrayList<String>()
        def result = jdbcTemplate.queryForList(query)
        result.each {
            providers.add(it.get("provider_code").toString())
        }
        providers
    }

    String getRandomActiveExcludedIssuer() {
        String query = "select distinct(iss.issuer_code)\n" +
                "from cpm.cpm_issuer iss\n" +
                "join cpm.cpm_issuance_offer io\n" +
                "on iss.issuer_id = io.issuer_id\n" +
                "where iss.is_active = 1\n" +
                "and iss.effective_begin_date <= now()\n" +
                "and iss.effective_end_date > now()\n" +
                "and io.is_active = 1\n" +
                "and io.effective_begin_date <= now()\n" +
                "and io.offer_end_date >= now()\n" +
                "and iss.issuer_code in (\n" +
                "'ACAE', 'ADEE', 'AIRM', 'ALCA', 'AMIB',\n" +
                "'AMIE', 'AMIP', 'AMPB', 'AMRP', 'ANNI',\n" +
                "'ATTE', 'BBMI', 'BBNF', 'BIST', 'BSIG',\n" +
                "'CARE', 'CASH', 'DORM', 'EARN', 'ENEX',\n" +
                "'GCIE', 'GNWB', 'HLCB', 'IGAE', 'LBS1',\n" +
                "'LEIB', 'LEIN', 'LHEE', 'LIEX', 'LITE',\n" +
                "'LIVE', 'LMGB', 'LMGC', 'LMGE', 'LMGL',\n" +
                "'LMGR', 'LPCA', 'LP2P', 'LSCF', 'MOOI',\n" +
                "'OGAM', 'OPSM', 'PALM', 'PB4U', 'PHIE',\n" +
                "'PMCI', 'RCGG', 'RMAC', 'RMGL', 'SFXE',\n" +
                "'SSGG', 'SSGM', 'SYME', 'TEST', 'TPBE',\n" +
                "'TRAO', 'TRFW', 'UTFE', 'VOYA', 'WHEE',\n" +
                "'0022', '1230', '6038'\n" +
                ")\n" +
                "and io.millage_amt > 50\n" +
                "and io.millage_type = 580"

        com.loyalty.collector.components.util.TestDataUtils.getRandomRecordFromList(
                jdbcTemplate.queryForList(query)
        ).getAt("issuer_code").toString()
    }

    String getRandomActiveCardNumberFromList(List<String> cardNumberList) {
        String query = "select mem.member_id " +
                "from cpm.cpm_member mem " +
                "JOIN CPM.cpm_member_card mc " +
                "ON mc.member_id=mem.member_id " +
                "join cpm.cpm_mbr_earning_pref mep " +
                "on mep.member_id = mem.member_id " +
                "join cpm.cpm_customer cu\n" +
                "on mem.customer_id = cu.customer_id" +
                "and mem.`status` = '${com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue()}' " +
                "and mem.is_ghost = '0' " +
                "and mem.test_member = 0 " +
                "and mep.earning_ratio != 100 " +
                "and mem.membership_type != 829 " +
                "and mep.is_active = 1 " +
                "and cu.email not in ('abc@abc.com', 'test@test.com') " +
                "and mem.member_id in (${cardNumberList.toString().replace('[', '').replace(']', '')})"

        com.loyalty.collector.components.util.TestDataUtils.getRandomRecordFromList(
                jdbcTemplate.queryForList(query)
        ).getAt("member_id").toString()
    }


    String getPostalCode(String cardNumber) {
        String query = "select adr.postal_code from cpm.cpm_member_card mc\n" +
                "join cpm.cpm_member mem\n" +
                "on mem.member_id = mc.member_id\n" +
                "join cpm.cpm_customer cu\n" +
                "on cu.customer_id = mem.customer_id\n" +
                "join cpm.cpm_cust_address ca\n" +
                "on ca.customer_id = cu.customer_id\n" +
                "join cpm.cpm_address adr\n" +
                "on adr.address_id = ca.address_id\n" +
                "where mc.card_id = $cardNumber and ca.`type`=42 and ca.is_active=1"
        String postalCode = jdbcTemplate.queryForMap(query).getAt("postal_code").toString()
        postalCode
    }

    String getExistingEmail() {
        String query = "select cu.email from cpm.cpm_member mem\n" +
                "join cpm.cpm_customer cu\n" +
                "on cu.customer_id = mem.customer_id\n" +
                "and cu.email not in (\n" +
                "'abc@abc.com', \n" +
                "'test@test.com', \n" +
                "'hellothere@gmail.com', \n" +
                "'CoUZM@gmai', \n" +
                "'CoUZM@gmail.com', \n" +
                "'abc^&amp;@#@loyalty.com', \n" +
                "'petersung2288822@gmail.com', \n" +
                "'saiful.islam30@gmail.com', \n" +
                "'lvukadin@loyalty.com', \n" +
                "'awcawec@aedcace.cas', \n" +
                "'jbulatao@loyalty.com', \n" +
                "'abc@loyalty.com', \n" +
                "'15ekha@loyalty.com',\n" +
                "'test@loyalty.com')\n" +
                "and cu.email like '%_@__%.___%'\n" +
                "limit 100"
        com.loyalty.collector.components.util.TestDataUtils.getRandomValueFromList(jdbcTemplate.queryForList(query)).getAt("email").toString()
    }



    String getRandomIssuer() {
        getRandomActiveIssuer()
    }

    String getRandomCardNumber() {
        getMemberCardId(com.loyalty.collector.components.util.TestDataUtils.getRandomRecordFromList(getRandomCollectorsListByStatus(com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue())))
    }


    String getRandomCardNumberFrench() {
        getMemberCardId(com.loyalty.collector.components.util.TestDataUtils.getRandomRecordFromList(getRandomFrenchCollectorsListByStatus(com.loyalty.collector.components.constant.MembershipStatus.ACTIVE.getValue())))
    }

    String getCollectorStatus(String cardNumber) {
        String query = "select status from cpm.cpm_member where member_id='$cardNumber'"
        jdbcTemplate.queryForObject(query, Long.class).toString()
    }




}