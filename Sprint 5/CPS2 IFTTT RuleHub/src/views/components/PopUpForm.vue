<template>
    <div class="card">
      <div class="pop-up-overlay" v-if="show">
        <div class="pop-up-form">
          <form class="p-3">
            <h6 class="mb-4">Add Rules</h6>
            <div class="row">
                  <div class="col">
                    <div class="form-group mb-4">
                      <label for="condition-name-1" class="form-label">Condition Name 1</label>
                      <input id="condition-name-1" type="text" class="form-control" v-model="rules.conditions[0].name" />
                    </div>
                  </div>
                  <!-- <div class="col d-flex align-items-center justify-content-center">
                    <div class="and-or">&/or</div>
                  </div> -->
                  <div class="col">
                    <div class="form-group mb-4">
                      <label for="condition-name-2" class="form-label">Condition Name 2</label>
                      <input id="condition-name-2" type="text" class="form-control" v-model="rules.conditions[1].name" />
                    </div>
                  </div>
                </div>
            <div class="row">
              <div class="col-md-2 form-group mb-4">
                <label for="value1" class="form-label">Value 1</label>
                <input id="value1" type="number" class="form-control" v-model="rules.conditions[0].conditionValue" />
              </div>
              <div class="col-md-2 form-group mb-4">
                <label for="units1" class="form-label">Units 1</label>
                <select id="units1" class="form-select" v-model="rules.conditions[0].conditionUnit">
                  <option value="">Unit</option>
                  <option value="hour(s)">Hour</option>
                  <option value="minute(s)">Minute</option>
                  <option value="celsius">°C</option>
                </select>
              </div>
              <div class="col-md-2 form-group mb-4">
                <label for="type1" class="form-label">Type 1</label>
                <select id="type1" class="form-select" v-model="rules.conditions[0].conditionType">
                  <option value="">Type</option>
                  <option value="every">Every</option>
                  <option value="above">Above</option>
                  <option value="below">Below</option>
                </select>
              </div>
              <div class="col-md-2 form-group mb-4">
                <label for="value2" class="form-label">Value 2</label>
                <input id="value2" type="number" class="form-control" v-model="rules.conditions[1].conditionValue" />
              </div>
              <div class="col-md-2 form-group mb-4">
                <label for="units2" class="form-label">Units 2</label>
                <select id="units2" class="form-select" v-model="rules.conditions[1].conditionUnit">
                  <option value="">Unit</option>
                  <option value="hour(s)">Hour</option>
                  <option value="minute(s)">Minute</option>
                  <option value="celsius">°C</option>
                </select>
              </div>
              <div class="col-md-2 form-group mb-4">
                <label for="type2" class="form-label">Type 2</label>
                <select id="type2" class="form-select" v-model="rules.conditions[1].conditionType">
                  <option value="">Type</option>
                  <option value="every">Every</option>
                  <option value="above">Above</option>
                  <option value="below">Below</option>
                </select>
              </div>
            </div>
            <div class="form-group mb-4">
            <label for="action" class="form-label">Action</label>
            <select id="action" class="form-select" v-model="rules.action" >
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
                <input id="is-active" type="checkbox" class="form-check-input" v-model="rules.active" />
                <label for="is-active" class="form-check-label">Is Active</label>
              </div>
            </div>
            <div class="d-flex justify-content-end">
              <soft-button color="secondary" variant="gradient" class="me-2" @click="closeForm" formnovalidate>Cancel</soft-button>
                <soft-button variant="gradient" class="me-2" @click="addNewRule">Save</soft-button>
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
  
  .pop-up-form {
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

  .and-or {
  font-size: 1.2rem;
  font-weight: bold;
  }
  </style>
  <script>
  import SoftButton from "@/components/SoftButton.vue";
  import axiosInstance from "@/services/axiosConfig"; // Import your custom-configured Axios instance
  export default {
    name: "ConditionForm",
    props: {
      show: {
        type: Boolean,
        default: false,
      },
    },
    components: {
      SoftButton,
    },
    async mounted() {
      let result = await axiosInstance.get("/actions"); // Use the custom-configured Axios instance
      console.log(result.data);
      this.actions = result.data;
    },
    data() {
      return {
        rules: {
          action: "",
          time: "",
          date: "",
          isActive: "",
          conditions: [
            {
              name: "",
              conditionType: "",
              conditionValue: "",
              conditionUnit: "",
            },
            {
              name: "",
              conditionType: "",
              conditionValue: "",
              conditionUnit: "",
            },
          ],
        },
      };
    },
    methods: {
      closeForm() {
        this.$emit("show-form", false);
      },
      async addNewRule() {
        if (
          this.rules.conditions.every(
            (condition) =>
              condition.name &&
              condition.conditionType &&
              condition.conditionValue &&
              condition.conditionUnit
          ) &&
          this.rules.action &&
          this.rules.time &&
          this.rules.date
        ) {
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
  
            if (!this.rules.active) {
              this.rules.active = false;
            }
  
            const result = await axiosInstance.post("/rules", {
              conditions,
              action: this.rules.action,
              time: this.rules.time,
              date: this.rules.date,
              isActive: this.rules.active,
            });
  
            if (result.status === 201) {
              // Emit an event to the parent component to close the form
              this.$emit("show-form", false);
            }
          } catch (error) {
            // Handle errors
            console.log(error);
          }
        }
      },
    },
  };
  </script>
  