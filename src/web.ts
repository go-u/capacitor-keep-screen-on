import { WebPlugin } from '@capacitor/core';
import {CapacitorKeepScreenOnPlugin, SetResult} from './definitions';

export class CapacitorKeepScreenOnWeb extends WebPlugin implements CapacitorKeepScreenOnPlugin {
  constructor() {
    super({
      name: 'CapacitorKeepScreenOn',
      platforms: ['web']
    });
  }

  async enable(): Promise<SetResult> {
    return Promise.resolve({ isEnabled: undefined });
  }

  async disable(): Promise<SetResult> {
    return Promise.resolve({ isEnabled: undefined });
  }

  async getState(): Promise<SetResult> {
    return Promise.resolve({ isEnabled: undefined });
  }
}

const CapacitorKeepScreenOn = new CapacitorKeepScreenOnWeb();

export { CapacitorKeepScreenOn };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorKeepScreenOn);
