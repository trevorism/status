<template>
  <div class="container">
    <b-table :data="tableData" :paginated="true" :per-page="10" default-sort="datecreated"
             default-sort-direction="desc">
      <template slot-scope="props">
        <b-table-column label="Topic">
          {{ props.row.topic }}
        </b-table-column>
        <b-table-column field="id" label="ID">
          {{ props.row.id }}
        </b-table-column>
        <b-table-column field="datecreated" label="Date" sortable>
          {{ new Date(props.row.datecreated).toJSON()}}
        </b-table-column>
        <b-table-column field="correlationid" label="Correlation ID">
          {{ props.row.correlationid }}
        </b-table-column>
        <b-table-column label="Actions">
          <b-button @click="showDetails(props.row.eventDetailsForDisplay)">Details</b-button>
          <b-button>View Flow</b-button>
        </b-table-column>
      </template>
    </b-table>
    <b-modal :active.sync="isModalActive">
      <div class="modal-card" style="width: auto">
        <header class="modal-card-head">
          <p class="modal-card-title">Event Details</p>
        </header>
        <section class="modal-card-body">
          <small class="container">{{this.currentDetails}}</small>
        </section>
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: 'Grid',
  props: ['details', 'selectedItem'],
  data () {
    return {
      collapsedData: [],
      isModalActive: false,
      currentDetails: null
    }
  },
  methods: {
    createAllData: function () {
      let allData = []
      for (let key of Object.keys(this.details)) {
        allData.push(...this.details[key])
      }
      this.collapsedData = allData
    },
    showDetails: function (details) {
      this.isModalActive = true
      this.currentDetails = details
    }
  },
  computed: {
    tableData: function () {
      if (this.selectedItem === '_all') {
        this.createAllData()
        return this.collapsedData
      }
      return this.details[this.selectedItem]
    }
  }
}
</script>

<style scoped>

</style>
