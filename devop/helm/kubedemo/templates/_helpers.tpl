{{- define "kubedemo.fullname" -}}
{{- printf "%s-%s" .Release.Name "kubedemo" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "kubedemo.labels" -}}
app.kubernetes.io/name: {{ include "kubedemo.fullname" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/version: {{ .Chart.AppVersion }}
app.kubernetes.io/managed-by: Helm
{{- end -}}
