<template>
  <div class="columns is-variable is-5 is-centered">
    <div v-for="item in flowItems" :key="item.topic">
      <div class="column" v-if="item.type === 'topic'">
        <div class="centerText">
          <b>{{item.topic}}</b> <br/>
          {{item.count}} <br/>
          {{item.date.toISOString().slice(0,10)}} <br/>
          {{item.date.toLocaleTimeString()}}
        </div>
      </div>
      <div class="column" v-else>
        <div class="centerText">
          <br/>
          ---&gt; <br/>
          {{item.millis}} Milliseconds
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EventFlow',
  props: ['allData', 'selectedRow'],
  mounted () {
    this.flowItems = []

    if (this.selectedRow.correlationid === null) {
      this.flowItems.push({type: 'topic', topic: this.selectedRow.topic, count: 1, date: new Date(this.selectedRow.datecreated)})
      return
    }

    let collectedItems = this.collectAllDataIntoObject()
    let firstCollectedItem = collectedItems[Object.keys(collectedItems)[0]]
    this.flowItems.push({type: 'topic', topic: Object.keys(collectedItems)[0], count: firstCollectedItem.count, date: new Date(firstCollectedItem.millis)})

    for (let i = 1; i < Object.keys(collectedItems).length; i++) {
      let previousCollectedItem = collectedItems[Object.keys(collectedItems)[i - 1]]
      let currentCollectedItem = collectedItems[Object.keys(collectedItems)[i]]
      this.flowItems.push({type: 'arrow', millis: currentCollectedItem.millis - previousCollectedItem.millis})
      this.flowItems.push({type: 'topic', topic: Object.keys(collectedItems)[i], count: currentCollectedItem.count, date: new Date(currentCollectedItem.millis)})
    }
  },
  data () {
    return {
      flowItems: []
    }
  },
  methods: {
    collectAllDataIntoObject: function () {
      let collectedItems = {}
      for (let item of this.allData) {
        if (item.correlationid === this.selectedRow.correlationid) {
          let existing = collectedItems[item.topic]
          if (existing) {
            existing.count++
          } else {
            existing = {}
            existing.count = 1
            existing.millis = item.datecreated
          }
          collectedItems[item.topic] = existing
        }
      }
      return collectedItems
    }
  }
}
</script>

<style scoped>
  .centerText {
    text-align: center;
  }
</style>
