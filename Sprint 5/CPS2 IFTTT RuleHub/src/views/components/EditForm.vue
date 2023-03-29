<template>
    <div class="card">
      <div class="pop-up-overlay" v-if="show">
        <div class="edit-form">
          <form class="p-3">
            <h6 class="mb-4">Edit Rules</h6>
            <div class="row">
                <div class="col">
                  <div class="col">
                      <div class="form-group mb-4">
                        <label for="condition-name-1" class="form-label">Condition Name</label>
                        <input id="condition-name-1" type="text" class="form-control" v-model="rules.condition.name" disabled />
                      </div>
                    </div>
                </div>
              </div>
            <div class="row">
              <div class="col-md-2 form-group mb-4">
                <label for="value1" class="form-label">Value 1</label>
                <input id="value1" type="number" class="form-control" v-model="rules.conditions.conditionValue" />
              </div>
            </div>
  
              <div class="col-md-2 form-group mb-4">
                <label for="units1" class="form-label">Units 1</label>
                <select id="units1" class="form-select" v-model="rules.condition.conditionUnit">
                  <option value="">Unit</option>
                  <option value="hour(s)">Hour</option>
                  <option value="minute(s)">Minute</option>
                  <option value="celsius">°C</option>
                </select>
              </div>
              <div class="col-md-2 form-group mb-4">
                <label for="type1" class="form-label">Type 1</label>
                <select id="type1" class="form-select" v-model="rules.condition.conditionType">
                  <option value="">Type</option>
                  <option value="every">Every</option>
                  <option value="above">Above</option>
                  <option value="below">Below</option>
                </select>
              </div>
            <div class="form-group mb-4">
              <label for="action" class="form-label">Action</label>
              <select id="action" class="form-select" v-model="rules.action.name" disabled>
                <option value="">Select an action</option>
                <option v-for="action in actions" :value="action.name" :key="action.id">{{ action.name }}</option>
              </select>
            </div>
            <div class="form-group mb-4">
            <label for="date" class="form-label">Date</label>
            <input id="date" type="date" class="form-control" v-model="rules.date" />
            </div>
            <div class="form-group mb-4">
            <label for="time" class="form-label">Time</label>
            <input id="time" type="time" class="form-control" v-model="rules.time" />
            </div>
            <div class="form-group mb-4">
              <div class="form-check">
                <input id="is-active" type="checkbox" class="form-check-input" v-model="rules.isActive" />
                <label for="is-active" class="form-check-label">Is Active</label>
              </div>
            </div>
            <div class="d-flex justify-content-end">
              <soft-button color="secondary" variant="gradient" class="me-2" @click="closeForm" formnovalidate>Cancel</soft-button>
                <soft-button variant="gradient" class="me-2" @click="editRule">Save</soft-button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </template>
  
  
  <style>
  .pop-up-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 9999;
  }
  
  .edit-form {
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    max-width: 900px;
    width: 100%;
    position: relative;
  }
  
  .close {
    position: absolute;
    font-size: 25px;
    top: 10px;
    right: 10px;
    cursor: pointer;
  }
  </style>
  
  <script>
  import SoftButton from "@/components/SoftButton.vue";
  import axiosInstance from "@/services/axiosConfig"; // Import your custom-configured Axios instance
  export default {
    components: {
      SoftButton,
    },
    name: "EditForm",
    props: {
      show: {
        type: Boolean,
        required: true,
      },
      rule: {
        type: Object,
        required: true,
      },
    },
    data() {
      return {
        rules: this.rule,
        actions: [],
      };
    },
    watch: {
      rule: {
        deep: true,
        immediate: true,
        handler() {
          this.fetchRuleData();
        },
      },
    },
    mounted() {
      this.fetchActions();
    },
    methods: {
      async fetchActions() {
        try {
          let result = await axiosInstance.get("/actions"); // Use the custom-configured Axios instance
          console.log(result.data);
          this.actions = result.data;
        } catch (error) {
          console.log(error);
        }
      },
      async fetchRuleData() {
        let result = await axiosInstance.get(`/rules/${this.rule.id}`); // Use the custom-configured Axios instance
        this.rules = result.data;
      },
  
      closeForm() {
        this.$emit("edit-form", false);
      },
  
      async editRule() {
        try {
          const conditions = this.rules.conditions.map((condition) => {
            let valueToSend = condition.conditionValue;
  
            if (condition.conditionUnit === "hour(s)") {
              valueToSend = condition.conditionValue * 60; // Convert hours to minutes
            }
  
            return {
              ...condition,
              value: valueToSend,
              originalValue: condition.conditionValue,
            };
          });
  
          if (!this.rules.isActive) {
            this.rules.isActive = false;
          }
  
          const result = await axiosInstance.put(`/rules/${this.rules.id}`, {
            conditions,
            action: this.rules.action,
            time: this.rules.time,
            date: this.rules.date,
            isActive: this.rules.isActive,
          });
  
          if (result.status === 200) {
            // Emit an event to the parent component to close the form
            this.$emit("edit-form", false);
          }
        } catch (error) {
          // Handle errors
          console.log(error);
        }
      },
    },
  };
  </script>
  