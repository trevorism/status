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
          <TopicMenu :allTopics="topics" v-on:menuSelected="setGridData"></TopicMenu>
        </div>
      </div>
      <div class="column">
        <Grid :selectedItem="selectedItem" :details="details"></Grid>
      </div>
    </section>

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
        this.loaded = true
        this.getAllDetails()
      })
      .catch(() => {
        this.message = 'Error loading status grid. Please refresh.'
      })
  },
  data () {
    return {
      topics: [],
      details: {},
      selectedItem: '_all',
      loaded: false,
      message: 'Loading...'
    }
  },
  methods: {
    getDetails: function (topicName) {
      axios.get('/api/status/topic/' + topicName)
        .then(response => {
          this.details[topicName] = response.data
        })
        .catch(() => {
          this.message = 'Error loading status details. Please refresh.'
        })
    },
    getAllDetails: function () {
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
