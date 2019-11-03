<template>
  <b-menu>
    <b-menu-list label="Topics">
      <b-menu-item label="All"></b-menu-item>
    </b-menu-list>
  </b-menu>
</template>

<script>
import axios from 'axios'

export default {

  name: 'TopicMenu',
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
