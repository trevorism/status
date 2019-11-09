<template>
  <div id="app">
    <section class="hero is-warning">
      <div class="hero-body">
        <h1 class="title">Trevorism Event Status</h1>
      </div>
    </section>
    <section class="columns">
      <div class="column is-one-fifth">
        <div class="sidebar">
          <TopicMenu :allTopics="topics" :detailsLoaded="detailsLoaded" v-on:menuSelected="setGridData" :key="detailsLoaded._all_count" :selectedItem="selectedItem"></TopicMenu>
        </div>
      </div>
      <div class="column">
        <Grid :selectedItem="selectedItem" :details="details" :key="detailsLoaded._all"></Grid>
      </div>
    </section>
    <b-loading :is-full-page="true" :active.sync="showLoading" :can-cancel="false"></b-loading>
  </div>
</template>

<script>
import axios from 'axios'
import Grid from './components/Grid.vue'
import TopicMenu from './components/TopicMenu.vue'

export default {
  name: 'App',
  components: {
    Grid, TopicMenu
  },
  mounted () {
    axios.get('/api/status/topic')
      .then(response => {
        this.topics = response.data
        this.showLoading = false
        this.getAllDetails()
      })
      .catch(() => {
        alert('Error loading status grid. Please refresh.')
      })
  },
  data () {
    return {
      topics: [],
      details: {},
      detailsLoaded: {_all: false, _all_count: 0},
      selectedItem: '_all',
      showLoading: true
    }
  },
  methods: {
    getDetails: function (topicName) {
      axios.get('/api/status/topic/' + topicName)
        .then(response => {
          this.details[topicName] = response.data
          this.detailsLoaded[topicName] = true
          this.detailsLoaded._all_count++
          if (this.detailsLoaded._all_count === this.topics.length) {
            this.detailsLoaded._all = true
          }
        })
        .catch(() => {
          console.log('Error loading status details. Please refresh.')
        })
    },
    getAllDetails: function () {
      this.detailsLoaded = {_all: false, _all_count: 0}
      for (let topicName of this.topics) {
        this.getDetails(topicName)
      }
    },
    setGridData: function (value) {
      this.selectedItem = value
    }
  }
}
</script>

<style>
  body::-webkit-scrollbar {
    display: none;
  }

  .sidebar {
    width: 250px
  }
</style>
