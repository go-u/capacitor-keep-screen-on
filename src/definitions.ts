declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorKeepScreenOn: CapacitorKeepScreenOnPlugin;
  }
}

export interface CapacitorKeepScreenOnPlugin {
  enable(): Promise<SetResult>;
  disable(): Promise<SetResult>;
  getState(): Promise<SetResult>;
}

export class State{
  constructor(public isEnabled: boolean) {
  }
}

export type SetResult = State
