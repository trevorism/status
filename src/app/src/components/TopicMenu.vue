<template>
  <b-menu>
    <b-menu-list label="Topics">
      <b-menu-item @click="selected('_all')" :active="selectedItem === '_all'">
        <template slot="label" slot-scope="all">
          <div class="container">
            All
            <div v-if="!detailsLoaded._all" class="is-pulled-right">
              <b-icon icon="refresh" size="is-small"></b-icon>
            </div>
          </div>
        </template>
      </b-menu-item>
      <div v-for="topic in allTopics" v-bind:key="topic.id">
        <b-menu-item @click="selected(topic)" :active="selectedItem === topic">
          <template slot="label" slot-scope="props">
            <div class="container">
              {{topic}}
              <div v-if="!detailsLoaded[topic]" class="is-pulled-right">
                <b-icon icon="refresh" size="is-small"></b-icon>
              </div>
            </div>
          </template>
        </b-menu-item>
      </div>
    </b-menu-list>
  </b-menu>
</template>

<script>
export default {
  name: 'TopicMenu',
  props: ['allTopics', 'detailsLoaded', 'selectedItem'],
  data () {
    return {
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
