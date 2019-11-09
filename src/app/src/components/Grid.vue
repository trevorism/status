<template>
  <div class="container">
    <b-table :data="tableData" :paginated="true" :per-page="10" default-sort="datecreated"
             default-sort-direction="desc" detailed
             detail-key="id">
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
        <b-table-column label="Flow">
          <b-button @click="buttonClick(props.row)">View Flow</b-button>
        </b-table-column>
      </template>
      <template slot="detail" slot-scope="details">
        {{details.row.eventDetailsForDisplay}}
      </template>
    </b-table>
    <b-modal :active.sync="isModalActive">
      <div class="modal-card" style="width: auto">
        <header class="modal-card-head">
          <p class="modal-card-title">Event Flow Details</p>
        </header>
        <section class="modal-card-body">
          <EventFlow :allData="this.collapsedData" :selectedRow="this.currentDetails"></EventFlow>
        </section>
      </div>
    </b-modal>
  </div>
</template>

<script>
import EventFlow from './EventFlow.vue'

export default {
  name: 'Grid',
  components: {
    EventFlow
  },
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
    buttonClick: function (row) {
      this.createAllData()
      this.currentDetails = row
      this.isModalActive = true
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
