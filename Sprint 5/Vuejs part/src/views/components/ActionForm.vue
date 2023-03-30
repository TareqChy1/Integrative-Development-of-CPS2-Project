<template>
    <div class="card">
      <div class="pop-up-overlay" v-if="show">
        <div class="action-form">
          <form class="p-3">
            <h6 class="mb-4">Add Action</h6>
            <div class="form-group mb-4">
              <label for="action-id" class="form-label">Action ID</label>
              <input id="action-id" type="text" class="form-control" :value="autoActionId" disabled />
            </div>
            <div class="form-group mb-4">
              <label for="action-name" class="form-label">Action</label>
              <input id="action-name" type="text" class="form-control" v-model="actions.name" required/>
            </div>
            <div class="d-flex justify-content-end">
              <soft-button color="secondary" variant="gradient" class="me-2" @click="close" formnovalidate>Cancel</soft-button>
              <soft-button variant="gradient" class="me-2" @click="addNewAction">Save</soft-button>
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
  
    .action-form {
      background-color: white;
      padding: 20px;
      border-radius: 10px;
      max-width: 500px;
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
  name: "ActionForm",
  props: {
    show: {
      type: Boolean,
      default: false,
    },
  },
  components: {
    SoftButton,
  },
  data() {
    return {
      actions: {
        name: "",
      },
    };
  },
  methods: {
    close() {
      this.$emit("action-form", false);
    },
    async addNewAction() {
      if (this.actions.name) {
        try {
          const result_3 = await axiosInstance.post("/actions", {
            name: this.actions.name,
          });

          if (result_3.status === 201) {
            this.$emit("action-form", false);
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

  