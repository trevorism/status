<template>
  <div class="container">
    <div v-if="!loaded">
      {{message}}
    </div>
    <div v-else-if="loaded">
      <div v-for="topic in data" v-bind:key="topic.id">
        {{topic}} <br/>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Grid',
  mounted () {
    axios.get('/api/status/topic')
      .then(response => {
        this.data = response.data
        this.loaded = true
      })
      .catch(() => {
        this.message = 'Error loading status grid. Please refresh.'
      })
  },
  data () {
    return {
      data: [],
      loaded: false,
      message: 'Loading...'
    }
  }
}
</script>

<style scoped>

</style>
