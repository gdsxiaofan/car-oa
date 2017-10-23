<template>
  <div>
      <Form  :label-width="80" label-position="right" ref="plan">
        <Form-item label="早班：">
          <TimePicker transfer v-model="plan.day" type="timerange" placement="bottom-end" placeholder="选择时间" style="width: 168px"></TimePicker>
        </Form-item>
        <Form-item label="晚班:">
          <TimePicker transfer v-model="plan.night" type="timerange" placement="bottom-end" placeholder="选择时间" style="width: 168px"></TimePicker>
        </Form-item>
        <FormItem>
          <Button type="primary" @click="handleSubmit('formValidate')">提交</Button>
          <Button type="ghost" @click="handleReset('formValidate')" style="margin-left: 8px">重置</Button>
        </FormItem>
      </Form>
  </div>
</template>

<script>
  import {
    getPlan,
    updatePlan
  } from '../../api/role/role'

  export default {
    data() {
      return {
        plan:{
          day:['09:41:00', '12:00:00'],
          night:['09:41:00', '12:00:00']
        }
      }
    },
    methods: {
    },
    created() {
//      getPlan()
    }
  }
</script>

<style>

</style>
