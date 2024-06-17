import {Component, OnInit} from '@angular/core';
import {Feature, View} from "ol";
import TileLayer from 'ol/layer/Tile.js';
import OSM from 'ol/source/OSM.js';
import Map from 'ol/Map.js';
import {Point} from "ol/geom";
import ol from "ol/dist/ol";
import {Icon, Style} from "ol/style";
import VectorSource from "ol/source/Vector";
import VectorLayer from "ol/layer/Vector";
import {OGCMapTile} from "ol/source";
import {L} from "@angular/cdk/keycodes";

@Component({
  selector: 'app-open-street-map',
  templateUrl: './open-street-map.component.html',
  styleUrls: ['./open-street-map.component.css']
})
export class OpenStreetMapComponent implements OnInit{

  public map!: Map

  constructor() {
  }
  ngOnInit(): void {
    const iconFeature = new Feature({
      geometry: new Point([0, 0]),
      name: 'Null Island',
      population: 4000,
      rainfall: 500,
    });

    const iconStyle = new Style({
      image: new Icon({
        anchor: [0.5, 46],
        anchorXUnits: 'fraction',
        anchorYUnits: 'pixels',
        src: 'data/icon.png',
      }),
    });

    iconFeature.setStyle(iconStyle);

    const vectorSource = new VectorSource({
      features: [iconFeature],
    });

    const vectorLayer = new VectorLayer({
      source: vectorSource,
    });

    const rasterLayer = new TileLayer({
      source: new OGCMapTile({
        url: 'https://www.openstreetmap.org/#map=8/43.930/17.674',
        crossOrigin: '',
      }),
    });

    this.map = new Map({
      layers: [rasterLayer, vectorLayer],
      target: 'map',
      view: new View({
        center: [0, 0],
        zoom: 2,maxZoom: 18,
      }),
    });
  }
}
