<template>
  <q-page class="flex flex-center column q-gutter-y-md">
    <img alt="Quasar logo" src="~assets/quasar-logo-full.svg">
    <q-btn label="enable" @click="enable()"/>
    <q-btn label="disable" @click="disable()"/>
    <q-btn label="get state" @click="getState()"/>
  </q-page>
</template>

<script>
import { Plugins } from '@capacitor/core'
const { CapacitorKeepScreenOn } = Plugins

export default {
  name: 'PageIndex',
  methods: {
    enable: function () {
      CapacitorKeepScreenOn.enable()
        .then((res) => {
          console.log(res)
        }).catch((err) => {
          console.log(err)
        })
    },
    disable: function () {
      CapacitorKeepScreenOn.disable()
        .then((res) => {
          console.log(res)
        }).catch((err) => {
          console.log(err)
        })
    },
    getState: function () {
      CapacitorKeepScreenOn.getState()
        .then((res) => {
          alert(res.isEnabled)
        }).catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
