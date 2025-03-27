package cn.itbeien.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgentOrgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AgentOrgExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNull() {
            addCriterion("AGENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNotNull() {
            addCriterion("AGENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNameEqualTo(String value) {
            addCriterion("AGENT_NAME =", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotEqualTo(String value) {
            addCriterion("AGENT_NAME <>", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThan(String value) {
            addCriterion("AGENT_NAME >", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThanOrEqualTo(String value) {
            addCriterion("AGENT_NAME >=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThan(String value) {
            addCriterion("AGENT_NAME <", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThanOrEqualTo(String value) {
            addCriterion("AGENT_NAME <=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLike(String value) {
            addCriterion("AGENT_NAME like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotLike(String value) {
            addCriterion("AGENT_NAME not like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameIn(List<String> values) {
            addCriterion("AGENT_NAME in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotIn(List<String> values) {
            addCriterion("AGENT_NAME not in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameBetween(String value1, String value2) {
            addCriterion("AGENT_NAME between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotBetween(String value1, String value2) {
            addCriterion("AGENT_NAME not between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andContactPersonIsNull() {
            addCriterion("CONTACT_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andContactPersonIsNotNull() {
            addCriterion("CONTACT_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andContactPersonEqualTo(String value) {
            addCriterion("CONTACT_PERSON =", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotEqualTo(String value) {
            addCriterion("CONTACT_PERSON <>", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonGreaterThan(String value) {
            addCriterion("CONTACT_PERSON >", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_PERSON >=", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLessThan(String value) {
            addCriterion("CONTACT_PERSON <", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_PERSON <=", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLike(String value) {
            addCriterion("CONTACT_PERSON like", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotLike(String value) {
            addCriterion("CONTACT_PERSON not like", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonIn(List<String> values) {
            addCriterion("CONTACT_PERSON in", values, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotIn(List<String> values) {
            addCriterion("CONTACT_PERSON not in", values, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonBetween(String value1, String value2) {
            addCriterion("CONTACT_PERSON between", value1, value2, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotBetween(String value1, String value2) {
            addCriterion("CONTACT_PERSON not between", value1, value2, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNull() {
            addCriterion("CONTACT_TEL is null");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNotNull() {
            addCriterion("CONTACT_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andContactTelEqualTo(String value) {
            addCriterion("CONTACT_TEL =", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotEqualTo(String value) {
            addCriterion("CONTACT_TEL <>", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThan(String value) {
            addCriterion("CONTACT_TEL >", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_TEL >=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThan(String value) {
            addCriterion("CONTACT_TEL <", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_TEL <=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLike(String value) {
            addCriterion("CONTACT_TEL like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotLike(String value) {
            addCriterion("CONTACT_TEL not like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelIn(List<String> values) {
            addCriterion("CONTACT_TEL in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotIn(List<String> values) {
            addCriterion("CONTACT_TEL not in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelBetween(String value1, String value2) {
            addCriterion("CONTACT_TEL between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotBetween(String value1, String value2) {
            addCriterion("CONTACT_TEL not between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Integer value) {
            addCriterion("AREA_ID =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Integer value) {
            addCriterion("AREA_ID <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Integer value) {
            addCriterion("AREA_ID >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("AREA_ID >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Integer value) {
            addCriterion("AREA_ID <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("AREA_ID <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Integer> values) {
            addCriterion("AREA_ID in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Integer> values) {
            addCriterion("AREA_ID not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("AREA_ID between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("AREA_ID not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("CREATOR =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("CREATOR <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("CREATOR >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("CREATOR <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("CREATOR <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("CREATOR like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("CREATOR not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("CREATOR in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("CREATOR not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("CREATOR between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("CREATOR not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIsNull() {
            addCriterion("CREATOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIsNotNull() {
            addCriterion("CREATOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorNameEqualTo(String value) {
            addCriterion("CREATOR_NAME =", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotEqualTo(String value) {
            addCriterion("CREATOR_NAME <>", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameGreaterThan(String value) {
            addCriterion("CREATOR_NAME >", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR_NAME >=", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLessThan(String value) {
            addCriterion("CREATOR_NAME <", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLessThanOrEqualTo(String value) {
            addCriterion("CREATOR_NAME <=", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLike(String value) {
            addCriterion("CREATOR_NAME like", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotLike(String value) {
            addCriterion("CREATOR_NAME not like", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIn(List<String> values) {
            addCriterion("CREATOR_NAME in", values, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotIn(List<String> values) {
            addCriterion("CREATOR_NAME not in", values, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameBetween(String value1, String value2) {
            addCriterion("CREATOR_NAME between", value1, value2, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotBetween(String value1, String value2) {
            addCriterion("CREATOR_NAME not between", value1, value2, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("LEVEL =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("LEVEL <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("LEVEL >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("LEVEL >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("LEVEL <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("LEVEL <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("LEVEL like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("LEVEL not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("LEVEL in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("LEVEL not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("LEVEL between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("LEVEL not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andOrgAccountIsNull() {
            addCriterion("ORG_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andOrgAccountIsNotNull() {
            addCriterion("ORG_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAccountEqualTo(String value) {
            addCriterion("ORG_ACCOUNT =", value, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountNotEqualTo(String value) {
            addCriterion("ORG_ACCOUNT <>", value, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountGreaterThan(String value) {
            addCriterion("ORG_ACCOUNT >", value, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ACCOUNT >=", value, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountLessThan(String value) {
            addCriterion("ORG_ACCOUNT <", value, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountLessThanOrEqualTo(String value) {
            addCriterion("ORG_ACCOUNT <=", value, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountLike(String value) {
            addCriterion("ORG_ACCOUNT like", value, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountNotLike(String value) {
            addCriterion("ORG_ACCOUNT not like", value, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountIn(List<String> values) {
            addCriterion("ORG_ACCOUNT in", values, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountNotIn(List<String> values) {
            addCriterion("ORG_ACCOUNT not in", values, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountBetween(String value1, String value2) {
            addCriterion("ORG_ACCOUNT between", value1, value2, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andOrgAccountNotBetween(String value1, String value2) {
            addCriterion("ORG_ACCOUNT not between", value1, value2, "orgAccount");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("RATE is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("RATE is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(String value) {
            addCriterion("RATE =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(String value) {
            addCriterion("RATE <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(String value) {
            addCriterion("RATE >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(String value) {
            addCriterion("RATE >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(String value) {
            addCriterion("RATE <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(String value) {
            addCriterion("RATE <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLike(String value) {
            addCriterion("RATE like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotLike(String value) {
            addCriterion("RATE not like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<String> values) {
            addCriterion("RATE in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<String> values) {
            addCriterion("RATE not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(String value1, String value2) {
            addCriterion("RATE between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(String value1, String value2) {
            addCriterion("RATE not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andNodeIsNull() {
            addCriterion("NODE is null");
            return (Criteria) this;
        }

        public Criteria andNodeIsNotNull() {
            addCriterion("NODE is not null");
            return (Criteria) this;
        }

        public Criteria andNodeEqualTo(String value) {
            addCriterion("NODE =", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotEqualTo(String value) {
            addCriterion("NODE <>", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThan(String value) {
            addCriterion("NODE >", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThanOrEqualTo(String value) {
            addCriterion("NODE >=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThan(String value) {
            addCriterion("NODE <", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThanOrEqualTo(String value) {
            addCriterion("NODE <=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLike(String value) {
            addCriterion("NODE like", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotLike(String value) {
            addCriterion("NODE not like", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeIn(List<String> values) {
            addCriterion("NODE in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotIn(List<String> values) {
            addCriterion("NODE not in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeBetween(String value1, String value2) {
            addCriterion("NODE between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotBetween(String value1, String value2) {
            addCriterion("NODE not between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveIsNull() {
            addCriterion("legal_identityCard_positive is null");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveIsNotNull() {
            addCriterion("legal_identityCard_positive is not null");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveEqualTo(String value) {
            addCriterion("legal_identityCard_positive =", value, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveNotEqualTo(String value) {
            addCriterion("legal_identityCard_positive <>", value, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveGreaterThan(String value) {
            addCriterion("legal_identityCard_positive >", value, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveGreaterThanOrEqualTo(String value) {
            addCriterion("legal_identityCard_positive >=", value, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveLessThan(String value) {
            addCriterion("legal_identityCard_positive <", value, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveLessThanOrEqualTo(String value) {
            addCriterion("legal_identityCard_positive <=", value, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveLike(String value) {
            addCriterion("legal_identityCard_positive like", value, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveNotLike(String value) {
            addCriterion("legal_identityCard_positive not like", value, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveIn(List<String> values) {
            addCriterion("legal_identityCard_positive in", values, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveNotIn(List<String> values) {
            addCriterion("legal_identityCard_positive not in", values, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveBetween(String value1, String value2) {
            addCriterion("legal_identityCard_positive between", value1, value2, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardPositiveNotBetween(String value1, String value2) {
            addCriterion("legal_identityCard_positive not between", value1, value2, "legalIdentitycardPositive");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeIsNull() {
            addCriterion("legal_identitycard_opposite is null");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeIsNotNull() {
            addCriterion("legal_identitycard_opposite is not null");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeEqualTo(String value) {
            addCriterion("legal_identitycard_opposite =", value, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeNotEqualTo(String value) {
            addCriterion("legal_identitycard_opposite <>", value, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeGreaterThan(String value) {
            addCriterion("legal_identitycard_opposite >", value, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeGreaterThanOrEqualTo(String value) {
            addCriterion("legal_identitycard_opposite >=", value, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeLessThan(String value) {
            addCriterion("legal_identitycard_opposite <", value, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeLessThanOrEqualTo(String value) {
            addCriterion("legal_identitycard_opposite <=", value, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeLike(String value) {
            addCriterion("legal_identitycard_opposite like", value, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeNotLike(String value) {
            addCriterion("legal_identitycard_opposite not like", value, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeIn(List<String> values) {
            addCriterion("legal_identitycard_opposite in", values, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeNotIn(List<String> values) {
            addCriterion("legal_identitycard_opposite not in", values, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeBetween(String value1, String value2) {
            addCriterion("legal_identitycard_opposite between", value1, value2, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andLegalIdentitycardOppositeNotBetween(String value1, String value2) {
            addCriterion("legal_identitycard_opposite not between", value1, value2, "legalIdentitycardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIsNull() {
            addCriterion("BUSINESS_LICENSE is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIsNotNull() {
            addCriterion("BUSINESS_LICENSE is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseEqualTo(String value) {
            addCriterion("BUSINESS_LICENSE =", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotEqualTo(String value) {
            addCriterion("BUSINESS_LICENSE <>", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseGreaterThan(String value) {
            addCriterion("BUSINESS_LICENSE >", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_LICENSE >=", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLessThan(String value) {
            addCriterion("BUSINESS_LICENSE <", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_LICENSE <=", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLike(String value) {
            addCriterion("BUSINESS_LICENSE like", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotLike(String value) {
            addCriterion("BUSINESS_LICENSE not like", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIn(List<String> values) {
            addCriterion("BUSINESS_LICENSE in", values, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotIn(List<String> values) {
            addCriterion("BUSINESS_LICENSE not in", values, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseBetween(String value1, String value2) {
            addCriterion("BUSINESS_LICENSE between", value1, value2, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_LICENSE not between", value1, value2, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andAccountPermitIsNull() {
            addCriterion("ACCOUNT_PERMIT is null");
            return (Criteria) this;
        }

        public Criteria andAccountPermitIsNotNull() {
            addCriterion("ACCOUNT_PERMIT is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPermitEqualTo(String value) {
            addCriterion("ACCOUNT_PERMIT =", value, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitNotEqualTo(String value) {
            addCriterion("ACCOUNT_PERMIT <>", value, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitGreaterThan(String value) {
            addCriterion("ACCOUNT_PERMIT >", value, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_PERMIT >=", value, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitLessThan(String value) {
            addCriterion("ACCOUNT_PERMIT <", value, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_PERMIT <=", value, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitLike(String value) {
            addCriterion("ACCOUNT_PERMIT like", value, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitNotLike(String value) {
            addCriterion("ACCOUNT_PERMIT not like", value, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitIn(List<String> values) {
            addCriterion("ACCOUNT_PERMIT in", values, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitNotIn(List<String> values) {
            addCriterion("ACCOUNT_PERMIT not in", values, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitBetween(String value1, String value2) {
            addCriterion("ACCOUNT_PERMIT between", value1, value2, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andAccountPermitNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_PERMIT not between", value1, value2, "accountPermit");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIsNull() {
            addCriterion("LICENSE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIsNotNull() {
            addCriterion("LICENSE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberEqualTo(String value) {
            addCriterion("LICENSE_NUMBER =", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotEqualTo(String value) {
            addCriterion("LICENSE_NUMBER <>", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberGreaterThan(String value) {
            addCriterion("LICENSE_NUMBER >", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("LICENSE_NUMBER >=", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLessThan(String value) {
            addCriterion("LICENSE_NUMBER <", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLessThanOrEqualTo(String value) {
            addCriterion("LICENSE_NUMBER <=", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLike(String value) {
            addCriterion("LICENSE_NUMBER like", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotLike(String value) {
            addCriterion("LICENSE_NUMBER not like", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIn(List<String> values) {
            addCriterion("LICENSE_NUMBER in", values, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotIn(List<String> values) {
            addCriterion("LICENSE_NUMBER not in", values, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberBetween(String value1, String value2) {
            addCriterion("LICENSE_NUMBER between", value1, value2, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotBetween(String value1, String value2) {
            addCriterion("LICENSE_NUMBER not between", value1, value2, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberIsNull() {
            addCriterion("BANK_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andBankNumberIsNotNull() {
            addCriterion("BANK_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andBankNumberEqualTo(String value) {
            addCriterion("BANK_NUMBER =", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberNotEqualTo(String value) {
            addCriterion("BANK_NUMBER <>", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberGreaterThan(String value) {
            addCriterion("BANK_NUMBER >", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NUMBER >=", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberLessThan(String value) {
            addCriterion("BANK_NUMBER <", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberLessThanOrEqualTo(String value) {
            addCriterion("BANK_NUMBER <=", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberLike(String value) {
            addCriterion("BANK_NUMBER like", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberNotLike(String value) {
            addCriterion("BANK_NUMBER not like", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberIn(List<String> values) {
            addCriterion("BANK_NUMBER in", values, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberNotIn(List<String> values) {
            addCriterion("BANK_NUMBER not in", values, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberBetween(String value1, String value2) {
            addCriterion("BANK_NUMBER between", value1, value2, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberNotBetween(String value1, String value2) {
            addCriterion("BANK_NUMBER not between", value1, value2, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andImgInfoIsNull() {
            addCriterion("IMG_INFO is null");
            return (Criteria) this;
        }

        public Criteria andImgInfoIsNotNull() {
            addCriterion("IMG_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andImgInfoEqualTo(String value) {
            addCriterion("IMG_INFO =", value, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoNotEqualTo(String value) {
            addCriterion("IMG_INFO <>", value, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoGreaterThan(String value) {
            addCriterion("IMG_INFO >", value, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoGreaterThanOrEqualTo(String value) {
            addCriterion("IMG_INFO >=", value, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoLessThan(String value) {
            addCriterion("IMG_INFO <", value, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoLessThanOrEqualTo(String value) {
            addCriterion("IMG_INFO <=", value, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoLike(String value) {
            addCriterion("IMG_INFO like", value, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoNotLike(String value) {
            addCriterion("IMG_INFO not like", value, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoIn(List<String> values) {
            addCriterion("IMG_INFO in", values, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoNotIn(List<String> values) {
            addCriterion("IMG_INFO not in", values, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoBetween(String value1, String value2) {
            addCriterion("IMG_INFO between", value1, value2, "imgInfo");
            return (Criteria) this;
        }

        public Criteria andImgInfoNotBetween(String value1, String value2) {
            addCriterion("IMG_INFO not between", value1, value2, "imgInfo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}