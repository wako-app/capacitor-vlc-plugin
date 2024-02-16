import { WebPlugin } from '@capacitor/core';

import type { CapacitorVlcPlayerPlugin } from './definitions';

export class CapacitorVlcPlayerWeb extends WebPlugin implements CapacitorVlcPlayerPlugin {
  async stream(options: { link: string }): Promise<{ link: string }> {
    console.log('channels: ', options.link);
    return options;
  }
}
