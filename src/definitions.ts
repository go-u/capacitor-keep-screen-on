declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorKeepScreenOn: CapacitorKeepScreenOnPlugin;
  }
}

export interface CapacitorKeepScreenOnPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
}
