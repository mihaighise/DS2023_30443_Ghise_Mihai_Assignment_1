import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})

export class ChartComponent {

  generateRandomData = () => {
		var y  = 1000, dps = [];
		for(var i = 0; i < 1000; i++) {
			y += Math.ceil(Math.random() * 10 - 5);
			dps.push({ y: y});
		}
		return dps;
	}

	chartOptions = {
	  zoomEnabled: true,
	  exportEnabled: true,
	  theme: "light2",
	  title: {
		text: "Try Zooming & Panning"
	  },
	  data: [{
		type: "line",
		dataPoints: this.generateRandomData()
	  }]
	}
}
