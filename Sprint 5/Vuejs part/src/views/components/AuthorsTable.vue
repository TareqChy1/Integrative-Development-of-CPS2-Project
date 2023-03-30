<template>
  <div class="card mb-4">
    <div class="card-header pb-0 md-14 d-flex justify-content-between align-items-center mb-4">
      <h6 class="mb-0">Rules Table</h6>
      <div>
        <soft-button color="dark" variant="gradient" class="me-2" @click="addNewRule">
        <i class="fas fa-plus me-2"></i>
          Add New Rule
        </soft-button>  
      </div>
    </div>

    <div class="card-body px-0 pt-0 pb-2">
      <div class="table-responsive p-0">
        <table class="table align-items-center mb-0">
          <thead>
            <tr>
              <th
                class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
              >
                IF: Condition
              </th>
              <th
                class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
              >
                THEN:  Action
              </th>
              <th
                class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
              >
                Status
              </th>
              <th
                class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
              >
                Date/Time
              </th>
              <th class="text-secondary opacity-7"></th>
            </tr>
          </thead>
          <tbody>
                <tr v-for="rule in rules" :key="rule.id">
                  <td>
                    <div class="d-flex px-2 py-1">
                      <div> 
                        <h6 class="mb-0 text-sm">{{ rule.id }}.</h6>
                      </div>
                      <div class="d-flex flex-column justify-content-center ms-2">
                        <h6 class="mb-0 text-sm" v-for="condition in rule.condition" :key="condition.id">{{ condition.name }}: {{ condition.conditionType }} {{ condition.conditionValue }} {{ condition.conditionUnit }}</h6>
                      </div>
                    </div>
                  </td>
                  <td>
                    <div v-for="action in rule.action" :key="action.id">
                      <p class="text-xs font-weight-bold mb-0">{{ action.name }}</p>
                    </div>
                  </td>
                  <td class="align-middle text-center text-sm">
                    <soft-badge
                        :color="rule.active ? 'success' : 'secondary'"
                        variant="gradient"
                        size="sm"
                      >
                        {{ rule.active ? 'On' : 'Off' }}
                      </soft-badge>
                  </td>
                  <td class="align-middle text-center">
                    <span class="text-secondary text-xs font-weight-bold">{{ rule.date }} {{ rule.time }}</span>
                  </td>
                  <!-- <td class="align-middle">
                  <a href="javascript:;" class="text-secondary font-weight-bold text-xs"
                  @click="showEditForm(rule.id)" >
                    Edit
                  </a>
                  </td> -->
                  <td class="align-middle">
                    <a href="javascript:;" class="text-danger font-weight-bold text-xs" @click="deleteRule(rule)">
                      <i class="fa fa-trash"></i>
                      Delete
                    </a>
                  </td>
                </tr>
              </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import SoftButton from "@/components/SoftButton.vue";
import SoftBadge from "@/components/SoftBadge.vue";
import axiosInstance from "@/services/axiosConfig"; // Import your custom-configured Axios instance
export default {
  name: "authors-table",
  data() {
    return {
      rules: [],
    };
  },
  components: {
    SoftButton,
    SoftBadge,
  },
  async mounted() {
    let result = await axiosInstance.get("/rules"); // Use the custom-configured Axios instance
    console.log(result.data);
    this.rules = result.data;
  },
  methods: {
    addNewRule() {
      this.$emit("show-form", true);
    },

    addNewAction() {
      this.$emit("action-form", true);
    },

    showEditForm(id) {
      const rule = this.rules.find((r) => r.id === id);
      this.$emit("edit-form", true, rule);
    },

    async deleteRule(rule) {
      const result = await axiosInstance.delete(`/rules/${rule.id}`); // Use the custom-configured Axios instance
      console.log(result);
      this.rules = this.rules.filter((r) => r.id !== rule.id);
}
  },
};
</script>

