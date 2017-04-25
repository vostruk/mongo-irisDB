import { DatalogFrontPage } from './app.po';

describe('datalog-front App', () => {
  let page: DatalogFrontPage;

  beforeEach(() => {
    page = new DatalogFrontPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
