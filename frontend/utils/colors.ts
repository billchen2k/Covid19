export type SurfaceStyle = {
  strokeColor: string
  fillColor: string
}

const surfaceStyleA: SurfaceStyle = {
  strokeColor: '#5a8055',
  fillColor: '#a00040'
}

const surfaceStyleB: SurfaceStyle = {
  strokeColor: '#5a8055',
  fillColor: '#4d1b30'
}

const surfaceStyleC: SurfaceStyle = {
  strokeColor: '#5a8055',
  fillColor: '#e2c5c6'
}

const surfaceStyleD: SurfaceStyle = {
  strokeColor: '#1b4d30',
  fillColor: '#e1cbc8'
}

const surfaceStyleE: SurfaceStyle = {
  strokeColor: '#cc7004',
  fillColor: '#70cc04'
}

const surfaceStyleF: SurfaceStyle = {
  strokeColor: '#1b4d30',
  fillColor: '#4d1b30'
}

export function getGraphSeriesStyle(seriesLength: number) {
  switch (seriesLength) {
    case 1:
      return [surfaceStyleB]
    case 2:
      return [surfaceStyleA, surfaceStyleC]
    default:
      return [surfaceStyleA, surfaceStyleB, surfaceStyleC]
  }
}

export function getGraphSeriesColor(series: string) {
  switch (series) {
    case 'A':
    default:
      return surfaceStyleA
    case 'B':
      return surfaceStyleB
    case 'C':
      return surfaceStyleC
    case 'D':
      return surfaceStyleD
    case 'E':
      return surfaceStyleE
    case 'F':
      return surfaceStyleF
  }
}
