import { WebPlugin } from '@capacitor/core';
import { CapacitorKeepScreenOnPlugin } from './definitions';

export class CapacitorKeepScreenOnWeb extends WebPlugin implements CapacitorKeepScreenOnPlugin {
  constructor() {
    super({
      name: 'CapacitorKeepScreenOn',
      platforms: ['web']
    });
  }

  async echo(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return options;
  }
}

const CapacitorKeepScreenOn = new CapacitorKeepScreenOnWeb();

export { CapacitorKeepScreenOn };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorKeepScreenOn);
