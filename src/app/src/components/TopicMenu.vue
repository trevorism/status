<template>
  <b-menu>
    <b-menu-list label="Topics">
      <b-menu-item label="All" @click="selected('_all')"></b-menu-item>
      <div v-for="topic in allTopics" v-bind:key="topic.id">
        <b-menu-item :label=topic @click="selected(topic)"></b-menu-item>
      </div>
    </b-menu-list>
  </b-menu>
</template>

<script>
export default {
  name: 'TopicMenu',
  props: ['allTopics'],
  data () {
    return {
      data: this.allTopics,
      loaded: false,
      message: 'Loading...'
    }
  },
  methods: {
    selected: function (topicName) {
      this.$emit('menuSelected', topicName)
    }
  }
}
</script>

<style scoped>

</style>
