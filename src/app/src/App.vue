<template>
  <div id="app">
    <section class="hero is-primary">
      <div class="hero-body">
        <h1 class="title">Trevorism Event Status</h1>
      </div>
    </section>
    <section class="level">
      <div class="level-item">
        <div class="sidebar">
          <TopicMenu></TopicMenu>
        </div>
      </div>
      <div class="level-item">
        <Grid></Grid>
      </div>
    </section>

  </div>
</template>

<script>
import Grid from './components/Grid.vue'
import TopicMenu from './components/TopicMenu.vue'

export default {
  name: 'App',
  components: {
    Grid, TopicMenu
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
