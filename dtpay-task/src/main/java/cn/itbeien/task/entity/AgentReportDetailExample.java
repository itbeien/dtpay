package cn.itbeien.task.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgentReportDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AgentReportDetailExample() {
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

        public Criteria andAgentIdIsNull() {
            addCriterion("AGENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("AGENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(Long value) {
            addCriterion("AGENT_ID =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(Long value) {
            addCriterion("AGENT_ID <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(Long value) {
            addCriterion("AGENT_ID >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("AGENT_ID >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(Long value) {
            addCriterion("AGENT_ID <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(Long value) {
            addCriterion("AGENT_ID <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<Long> values) {
            addCriterion("AGENT_ID in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<Long> values) {
            addCriterion("AGENT_ID not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(Long value1, Long value2) {
            addCriterion("AGENT_ID between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(Long value1, Long value2) {
            addCriterion("AGENT_ID not between", value1, value2, "agentId");
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

        public Criteria andOrderNoIsNull() {
            addCriterion("ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("ORDER_NO =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("ORDER_NO <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("ORDER_NO >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_NO >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("ORDER_NO <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ORDER_NO <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("ORDER_NO like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("ORDER_NO not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("ORDER_NO in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("ORDER_NO not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("ORDER_NO between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("ORDER_NO not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeIsNull() {
            addCriterion("PAYWAY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeIsNotNull() {
            addCriterion("PAYWAY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeEqualTo(String value) {
            addCriterion("PAYWAY_CODE =", value, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeNotEqualTo(String value) {
            addCriterion("PAYWAY_CODE <>", value, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeGreaterThan(String value) {
            addCriterion("PAYWAY_CODE >", value, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PAYWAY_CODE >=", value, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeLessThan(String value) {
            addCriterion("PAYWAY_CODE <", value, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeLessThanOrEqualTo(String value) {
            addCriterion("PAYWAY_CODE <=", value, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeLike(String value) {
            addCriterion("PAYWAY_CODE like", value, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeNotLike(String value) {
            addCriterion("PAYWAY_CODE not like", value, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeIn(List<String> values) {
            addCriterion("PAYWAY_CODE in", values, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeNotIn(List<String> values) {
            addCriterion("PAYWAY_CODE not in", values, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeBetween(String value1, String value2) {
            addCriterion("PAYWAY_CODE between", value1, value2, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andPaywayCodeNotBetween(String value1, String value2) {
            addCriterion("PAYWAY_CODE not between", value1, value2, "paywayCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeIsNull() {
            addCriterion("SCENE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSceneCodeIsNotNull() {
            addCriterion("SCENE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSceneCodeEqualTo(String value) {
            addCriterion("SCENE_CODE =", value, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeNotEqualTo(String value) {
            addCriterion("SCENE_CODE <>", value, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeGreaterThan(String value) {
            addCriterion("SCENE_CODE >", value, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SCENE_CODE >=", value, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeLessThan(String value) {
            addCriterion("SCENE_CODE <", value, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeLessThanOrEqualTo(String value) {
            addCriterion("SCENE_CODE <=", value, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeLike(String value) {
            addCriterion("SCENE_CODE like", value, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeNotLike(String value) {
            addCriterion("SCENE_CODE not like", value, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeIn(List<String> values) {
            addCriterion("SCENE_CODE in", values, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeNotIn(List<String> values) {
            addCriterion("SCENE_CODE not in", values, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeBetween(String value1, String value2) {
            addCriterion("SCENE_CODE between", value1, value2, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andSceneCodeNotBetween(String value1, String value2) {
            addCriterion("SCENE_CODE not between", value1, value2, "sceneCode");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("AMOUNT =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("AMOUNT <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("AMOUNT >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMOUNT >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("AMOUNT <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMOUNT <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("AMOUNT in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("AMOUNT not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMOUNT between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMOUNT not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNull() {
            addCriterion("REFUND_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNotNull() {
            addCriterion("REFUND_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT =", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <>", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThan(BigDecimal value) {
            addCriterion("REFUND_AMOUNT >", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT >=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThan(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIn(List<BigDecimal> values) {
            addCriterion("REFUND_AMOUNT in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotIn(List<BigDecimal> values) {
            addCriterion("REFUND_AMOUNT not in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_AMOUNT between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_AMOUNT not between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andMerIncomeIsNull() {
            addCriterion("MER_INCOME is null");
            return (Criteria) this;
        }

        public Criteria andMerIncomeIsNotNull() {
            addCriterion("MER_INCOME is not null");
            return (Criteria) this;
        }

        public Criteria andMerIncomeEqualTo(BigDecimal value) {
            addCriterion("MER_INCOME =", value, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeNotEqualTo(BigDecimal value) {
            addCriterion("MER_INCOME <>", value, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeGreaterThan(BigDecimal value) {
            addCriterion("MER_INCOME >", value, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MER_INCOME >=", value, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeLessThan(BigDecimal value) {
            addCriterion("MER_INCOME <", value, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MER_INCOME <=", value, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeIn(List<BigDecimal> values) {
            addCriterion("MER_INCOME in", values, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeNotIn(List<BigDecimal> values) {
            addCriterion("MER_INCOME not in", values, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MER_INCOME between", value1, value2, "merIncome");
            return (Criteria) this;
        }

        public Criteria andMerIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MER_INCOME not between", value1, value2, "merIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeIsNull() {
            addCriterion("AGENT_INCOME is null");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeIsNotNull() {
            addCriterion("AGENT_INCOME is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeEqualTo(BigDecimal value) {
            addCriterion("AGENT_INCOME =", value, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeNotEqualTo(BigDecimal value) {
            addCriterion("AGENT_INCOME <>", value, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeGreaterThan(BigDecimal value) {
            addCriterion("AGENT_INCOME >", value, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AGENT_INCOME >=", value, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeLessThan(BigDecimal value) {
            addCriterion("AGENT_INCOME <", value, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AGENT_INCOME <=", value, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeIn(List<BigDecimal> values) {
            addCriterion("AGENT_INCOME in", values, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeNotIn(List<BigDecimal> values) {
            addCriterion("AGENT_INCOME not in", values, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AGENT_INCOME between", value1, value2, "agentIncome");
            return (Criteria) this;
        }

        public Criteria andAgentIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AGENT_INCOME not between", value1, value2, "agentIncome");
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

        public Criteria andTradeTimeIsNull() {
            addCriterion("TRADE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNotNull() {
            addCriterion("TRADE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeEqualTo(Date value) {
            addCriterion("TRADE_TIME =", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotEqualTo(Date value) {
            addCriterion("TRADE_TIME <>", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThan(Date value) {
            addCriterion("TRADE_TIME >", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TRADE_TIME >=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThan(Date value) {
            addCriterion("TRADE_TIME <", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThanOrEqualTo(Date value) {
            addCriterion("TRADE_TIME <=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIn(List<Date> values) {
            addCriterion("TRADE_TIME in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotIn(List<Date> values) {
            addCriterion("TRADE_TIME not in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeBetween(Date value1, Date value2) {
            addCriterion("TRADE_TIME between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotBetween(Date value1, Date value2) {
            addCriterion("TRADE_TIME not between", value1, value2, "tradeTime");
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